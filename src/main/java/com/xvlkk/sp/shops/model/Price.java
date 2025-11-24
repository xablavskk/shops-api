package com.xvlkk.sp.shops.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price")
@Getter
@Setter
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_price")
    private Long cdPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_product_sku")
    private ProductSku productSku;

    @Column(name = "nr_amount")
    private BigDecimal nrAmount;

    @Column(name = "ds_currency")
    private String dsCurrency;

    @Column(name = "dt_valid_from")
    private LocalDateTime dtValidFrom;

    @Column(name = "dt_valid_to")
    private LocalDateTime dtValidTo;

    @Column(name = "st_promotional")
    private Boolean stPromotional;
}

