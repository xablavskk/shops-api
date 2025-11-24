package com.xvlkk.sp.shops.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouponTypeEnum {
    PERCENTAGE("P"),
    FIXED("F");

    private final String tpCoupon;
}

