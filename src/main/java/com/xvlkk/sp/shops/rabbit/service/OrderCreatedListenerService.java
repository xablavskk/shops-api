package com.xvlkk.sp.shops.rabbit.service;

import com.xvlkk.sp.shops.dto.OrderDTO;
import com.xvlkk.sp.shops.rabbit.config.MessagingConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCreatedListenerService {
    @RabbitListener(queues = MessagingConfig.ORDER_CREATED_QUEUE)
    public void onOrderCreated(OrderDTO event) {
        System.out.println("📩 ORDER EVENT RECEIVED → " + event);
    }
}
