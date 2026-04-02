package com.xvlkk.sp.shops.service;

import com.xvlkk.sp.shops.dto.ShipmentDTO;
import com.xvlkk.sp.shops.enums.OrderStatusEnum;
import com.xvlkk.sp.shops.enums.ShipmentStatusEnum;
import com.xvlkk.sp.shops.exceptions.ResourceNotFoundException;
import com.xvlkk.sp.shops.mapper.ShipmentMapper;
import com.xvlkk.sp.shops.model.Order;
import com.xvlkk.sp.shops.model.Shipment;
import com.xvlkk.sp.shops.rabbit.service.ShipmentProducerService;
import com.xvlkk.sp.shops.repository.OrderRepository;
import com.xvlkk.sp.shops.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;
    private final ShipmentMapper shipmentMapper;
    private final ShipmentProducerService shipmentProducerService;

    public List<ShipmentDTO> findByOrder(Long cdOrder) {
        return shipmentRepository.findByOrderCdOrder(cdOrder).stream().map(shipmentMapper::toDto).toList();
    }

    public ShipmentDTO findById(Long id) {
        return shipmentMapper.toDto(findOrThrow(id));
    }

    @Transactional
    public ShipmentDTO create(Long cdOrder, ShipmentDTO dto) {
        Order order = orderRepository.findById(cdOrder)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + cdOrder));

        Shipment shipment = shipmentMapper.toEntity(dto);
        shipment.setOrder(order);
        shipment.setStShipment(ShipmentStatusEnum.CREATED.getStShipment());

        Shipment saved = shipmentRepository.save(shipment);
        shipmentProducerService.sendShipmentCreatedEvent(shipmentMapper.toDto(saved));
        return shipmentMapper.toDto(saved);
    }

    @Transactional
    public ShipmentDTO ship(Long id) {
        Shipment shipment = findOrThrow(id);
        shipment.setStShipment(ShipmentStatusEnum.SHIPPED.getStShipment());
        shipment.setDtShippedAt(LocalDateTime.now());

        Order order = shipment.getOrder();
        order.setStatus(OrderStatusEnum.SHIPPED.getStOrder());
        orderRepository.save(order);

        Shipment saved = shipmentRepository.save(shipment);
        shipmentProducerService.sendShipmentCreatedEvent(shipmentMapper.toDto(saved));
        return shipmentMapper.toDto(saved);
    }

    @Transactional
    public ShipmentDTO deliver(Long id) {
        Shipment shipment = findOrThrow(id);
        shipment.setStShipment(ShipmentStatusEnum.DELIVERED.getStShipment());
        shipment.setDtDeliveredAt(LocalDateTime.now());

        Order order = shipment.getOrder();
        order.setStatus(OrderStatusEnum.DELIVERED.getStOrder());
        orderRepository.save(order);

        return shipmentMapper.toDto(shipmentRepository.save(shipment));
    }

    private Shipment findOrThrow(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found: " + id));
    }
}
