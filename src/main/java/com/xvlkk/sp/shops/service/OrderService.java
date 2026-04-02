package com.xvlkk.sp.shops.service;

import com.xvlkk.sp.shops.dto.OrderDTO;
import com.xvlkk.sp.shops.enums.OrderStatusEnum;
import com.xvlkk.sp.shops.exceptions.BusinessException;
import com.xvlkk.sp.shops.exceptions.ResourceNotFoundException;
import com.xvlkk.sp.shops.mapper.OrderMapper;
import com.xvlkk.sp.shops.model.*;
import com.xvlkk.sp.shops.rabbit.service.OrderProducerService;
import com.xvlkk.sp.shops.repository.CartRepository;
import com.xvlkk.sp.shops.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderProducerService orderProducerService;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;
    private final InventoryService inventoryService;
    private final PriceService priceService;

    public Page<OrderDTO> findByUser(Long cdUser, Pageable pageable) {
        return orderRepository.findByUserCdUser(cdUser, pageable).map(orderMapper::toDto);
    }

    public OrderDTO findById(Long id) {
        return orderMapper.toDto(findOrThrow(id));
    }

    public OrderDTO create(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order = orderRepository.save(order);
        orderDTO = orderMapper.toDto(order);
        orderProducerService.sendOrderCreatedEvent(orderDTO);
        return orderDTO;
    }

    @Transactional
    public OrderDTO checkoutFromCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found: " + cartId));

        if (cart.getItems().isEmpty()) {
            throw new BusinessException("Cannot checkout an empty cart");
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setDsOrderNumber(UUID.randomUUID().toString());
        order.setStatus(OrderStatusEnum.CREATED.getStOrder());

        List<OrderItem> items = cart.getItems().stream().map(cartItem -> {
            ProductSku sku = cartItem.getProductSku();
            inventoryService.reserveStock(sku.getCdProductSku(), cartItem.getQtCartItem());

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductSku(sku);
            item.setNmProductAtOrder(sku.getProduct().getNmProduct());
            item.setDsAttributesAtOrder(sku.getAttributesJson());
            item.setQtOrderItem(cartItem.getQtCartItem());
            BigDecimal unitPrice = priceService.getEffectivePrice(sku.getCdProductSku());
            item.setVlUnitPrice(unitPrice);
            item.setVlTotalPrice(unitPrice.multiply(BigDecimal.valueOf(cartItem.getQtCartItem())));
            return item;
        }).toList();

        order.setItems(items);
        order.setQtTotalAmount(items.stream().map(OrderItem::getVlTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

        Order saved = orderRepository.save(order);

        // clear cart after checkout
        cart.getItems().clear();
        cartRepository.save(cart);

        OrderDTO dto = orderMapper.toDto(saved);
        orderProducerService.sendOrderCreatedEvent(dto);
        return dto;
    }

    public OrderDTO updateStatus(Long id, String status) {
        Order order = findOrThrow(id);
        order.setStatus(status);
        return orderMapper.toDto(orderRepository.save(order));
    }

    private Order findOrThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
    }
}
