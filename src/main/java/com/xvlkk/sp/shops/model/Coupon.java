package com.xvlkk.sp.shops.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupon")
@Getter
@Setter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_coupon")
    private Long cdCoupon;

    @Column(unique = true, name = "ds_code")
    private String dsCode;

    @Column(name = "tp_coupon")
    private String tpCoupon; // PERCENTAGE or FIXED -- USE ENUM COUPON TYPE

    @Column(name = "vl_coupon")
    private BigDecimal vlCoupon;

    @Column(name = "dt_valid_from")
    private LocalDateTime dtValidFrom;

    @Column(name = "dt_valid_to")
    private LocalDateTime dtValidTo;

    @Column(name = "qt_max_use")
    private Integer qtMaxUse;

    @Column(name = "qt_use")
    private Integer qtUse;
}

