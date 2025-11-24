package com.xvlkk.sp.shops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.Shipment}
 */
@Setter
@Getter
public class ShipmentDTO implements Serializable {
    private Long id;
    private String dsCarrier;
    private String dsTrackingNumber;
    private String stShipment;
    private LocalDateTime dtShippedAt;
    private LocalDateTime dtDeliveredAt;
}