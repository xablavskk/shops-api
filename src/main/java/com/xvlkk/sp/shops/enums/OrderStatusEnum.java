package com.xvlkk.sp.shops.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    CREATED("C"),
    PAID("P"),
    PROCESSING("R"),
    SHIPPED("S"),
    DELIVERED("D"),
    CANCELLED("L");

    private final String stOrder;
}
