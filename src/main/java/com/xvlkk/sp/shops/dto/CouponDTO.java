package com.xvlkk.sp.shops.dto;

import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Coupon code is required")
    @Size(max = 50)
    private String dsCode;

    @NotBlank(message = "Coupon type is required")
    private String tpCoupon;

    @NotNull(message = "Coupon value is required")
    @DecimalMin(value = "0.01", message = "Value must be greater than zero")
    private BigDecimal vlCoupon;

    @NotNull(message = "Valid from date is required")
    private LocalDateTime dtValidFrom;

    private LocalDateTime dtValidTo;

    @Min(value = 1, message = "Max use must be at least 1")
    private Integer qtMaxUse;

    private Integer qtUse;
}
