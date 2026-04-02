package com.xvlkk.sp.shops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.OrderItem}
 */
@Setter
@Getter
public class OrderItemDTO implements Serializable {
    private Long cdOrderItem;
    private String nmProductAtOrder;
    private String dsAttributesAtOrder;
    private BigDecimal vlUnitPrice;
    private Integer qtOrderItem;
    private BigDecimal vlTotalPrice;
    private OrderDTO order;
    private ProductSkuDTO productSku;
}