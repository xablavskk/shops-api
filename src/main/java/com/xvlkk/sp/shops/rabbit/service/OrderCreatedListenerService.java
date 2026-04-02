package com.xvlkk.sp.shops.rabbit.service;

import com.xvlkk.sp.shops.dto.OrderDTO;
import com.xvlkk.sp.shops.rabbit.config.MessagingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderCreatedListenerService {

    @RabbitListener(queues = MessagingConfig.ORDER_CREATED_QUEUE)
    public void onOrderCreated(OrderDTO event) {
        log.info("Order event received → orderNumber={} status={}", event.getDsOrderNumber(), event.getStatus());
        // TODO: send confirmation email, trigger inventory reservation, etc.
    }
}
