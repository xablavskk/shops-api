package com.xvlkk.sp.shops.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatusEnum {
    PENDING("P"),
    APPROVED("A"),
    DECLINED("D"),
    REFUNDED("R");

    private final String stPayment;
}

