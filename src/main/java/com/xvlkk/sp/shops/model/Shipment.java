package com.xvlkk.sp.shops.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipment")
@Getter
@Setter
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_shipment")
    private Long cdShipment;

    @Column(name = "ds_carrier")
    private String dsCarrier;

    @Column(name = "ds_tracking_number")
    private String dsTrackingNumber;

    @Column(name = "st_shipment")
    private String stShipment; // USE ENUM SHIPMENT STATUS

    @Column(name = "dt_shipped_at")
    private LocalDateTime dtShippedAt;

    @Column(name = "dt_delivered_at")
    private LocalDateTime dtDeliveredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_order", foreignKey = @ForeignKey(name = "fk_shipment_order_cd_order"))
    private Order order;
}

