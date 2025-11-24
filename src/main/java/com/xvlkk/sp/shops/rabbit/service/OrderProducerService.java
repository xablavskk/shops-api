package com.xvlkk.sp.shops.rabbit.service;

import com.xvlkk.sp.shops.rabbit.config.MessagingConfig;
import com.xvlkk.sp.shops.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducerService {
    private final AmqpTemplate amqpTemplate;

    public void sendOrderCreatedEvent(OrderDTO event) {
        amqpTemplate.convertAndSend(
                MessagingConfig.ORDER_EXCHANGE,
                MessagingConfig.ORDER_CREATED_ROUTING_KEY,
                event
        );
    }
}
