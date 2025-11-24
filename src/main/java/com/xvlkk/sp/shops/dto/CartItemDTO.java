package com.xvlkk.sp.shops.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.CartItem}
 */
@Getter
@Setter
public class CartItemDTO implements Serializable {
    private Long cdCartItem;
    private Integer qtCartItem;

    private CartDTO cart;
    private ProductSkuDTO productSku;
}