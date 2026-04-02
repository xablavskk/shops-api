package com.xvlkk.sp.shops.rabbit.service;

import com.xvlkk.sp.shops.dto.ShipmentDTO;
import com.xvlkk.sp.shops.rabbit.config.MessagingConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentProducerService {

    private final AmqpTemplate amqpTemplate;

    public void sendShipmentCreatedEvent(ShipmentDTO event) {
        amqpTemplate.convertAndSend(
                MessagingConfig.SHIPMENT_EXCHANGE,
                MessagingConfig.SHIPMENT_CREATED_ROUTING_KEY,
                event
        );
    }
}
