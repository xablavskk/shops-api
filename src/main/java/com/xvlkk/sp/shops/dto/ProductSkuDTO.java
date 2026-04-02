package com.xvlkk.sp.shops.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.ProductSku}
 */
@Getter
@Setter
public class ProductSkuDTO implements Serializable {
    private Long cdProductSku;
    private String dsSku;
    private String attributesJson;
}