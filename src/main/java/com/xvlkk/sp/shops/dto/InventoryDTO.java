package com.xvlkk.sp.shops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.Inventory}
 */
@Getter
@Setter
public class InventoryDTO implements Serializable {
    private Long cdInventory;
    private ProductSkuDTO productSku;
    private Integer qtAvailable;
    private Integer qtReserved;
}