package com.xvlkk.sp.shops.rabbit.service;

import com.xvlkk.sp.shops.dto.PaymentDTO;
import com.xvlkk.sp.shops.rabbit.config.MessagingConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducerService {

    private final AmqpTemplate amqpTemplate;

    public void sendPaymentCreatedEvent(PaymentDTO event) {
        amqpTemplate.convertAndSend(
                MessagingConfig.PAYMENT_EXCHANGE,
                MessagingConfig.PAYMENT_CREATED_ROUTING_KEY,
                event
        );
    }
}
