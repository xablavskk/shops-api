package com.xvlkk.sp.shops.rabbit.service;

import com.xvlkk.sp.shops.dto.ShipmentDTO;
import com.xvlkk.sp.shops.rabbit.config.MessagingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShipmentEventListenerService {

    @RabbitListener(queues = MessagingConfig.SHIPMENT_CREATED_QUEUE)
    public void onShipmentEvent(ShipmentDTO event) {
        log.info("Shipment event received → id={} status={}", event.getId(), event.getStShipment());
        // TODO: notify user, update tracking, etc.
    }
}
