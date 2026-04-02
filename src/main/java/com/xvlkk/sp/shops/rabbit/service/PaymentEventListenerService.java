package com.xvlkk.sp.shops.rabbit.service;

import com.xvlkk.sp.shops.dto.PaymentDTO;
import com.xvlkk.sp.shops.rabbit.config.MessagingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentEventListenerService {

    @RabbitListener(queues = MessagingConfig.PAYMENT_CREATED_QUEUE)
    public void onPaymentEvent(PaymentDTO event) {
        log.info("Payment event received → id={} status={}", event.getCdPayment(), event.getStatus());
        // TODO: notify user, trigger invoice generation, etc.
    }
}
