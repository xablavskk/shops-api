package com.xvlkk.sp.shops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.Price}
 */
@Setter
@Getter
public class PriceDTO implements Serializable {
    private Long cdPrice;
    private ProductSkuDTO sku;
    private BigDecimal nrAmount;
    private String dsCurrency;
    private LocalDateTime dtValidFrom;
    private LocalDateTime dtValidTo;
    private Boolean stPromotional;
}