package com.xvlkk.sp.shops.service;

import com.xvlkk.sp.shops.dto.OrderDTO;
import com.xvlkk.sp.shops.mapper.OrderMapper;
import com.xvlkk.sp.shops.model.Order;
import com.xvlkk.sp.shops.rabbit.service.OrderProducerService;
import com.xvlkk.sp.shops.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderProducerService orderProducerService;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderDTO create(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);

        order = orderRepository.save(order);

        orderDTO = orderMapper.toDto(order);

        orderProducerService.sendOrderCreatedEvent(orderDTO);

        return orderDTO;
    }
}
