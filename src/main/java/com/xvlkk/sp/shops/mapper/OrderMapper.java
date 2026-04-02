package com.xvlkk.sp.shops.mapper;

import com.xvlkk.sp.shops.dto.OrderDTO;
import com.xvlkk.sp.shops.model.Order;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order toEntity(OrderDTO orderDTO);

    @AfterMapping
    default void linkItems(@MappingTarget Order order) {
        order.getItems().forEach(item -> item.setOrder(order));
    }

    @AfterMapping
    default void linkPayments(@MappingTarget Order order) {
        order.getPayments().forEach(payment -> payment.setOrder(order));
    }

    @AfterMapping
    default void linkShipments(@MappingTarget Order order) {
        order.getShipments().forEach(shipment -> shipment.setOrder(order));
    }

    OrderDTO toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderDTO orderDTO, @MappingTarget Order order);
}