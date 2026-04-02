package com.xvlkk.sp.shops.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShipmentStatusEnum {
    CREATED("C"),
    SHIPPED("S"),
    DELIVERED("D");

    private final String stShipment;
}
