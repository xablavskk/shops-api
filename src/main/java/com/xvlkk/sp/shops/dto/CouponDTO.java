package com.xvlkk.sp.shops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.Coupon}
 */
@Getter
@Setter
public class CouponDTO implements Serializable {
    private Long cdCoupon;
    private String dsCode;
    private String tpCoupon;
    private BigDecimal vlCoupon;
    private LocalDateTime dtValidFrom;
    private LocalDateTime dtValidTo;
    private Integer qtMaxUse;
    private Integer qtUse;
}