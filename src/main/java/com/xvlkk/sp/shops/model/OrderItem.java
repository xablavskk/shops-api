package com.xvlkk.sp.shops.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_order_item")
    private Long cdOrderItem;

    @Column(name = "nm_product_at_order")
    private String nmProductAtOrder;

    @Column(name = "ds_attributes_at_order")
    private String dsAttributesAtOrder;

    @Column(name = "vl_unit_price")
    private BigDecimal vlUnitPrice;

    @Column(name = "qt_order_item")
    private Integer qtOrderItem;

    @Column(name = "vl_total_price")
    private BigDecimal vlTotalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_order", foreignKey = @ForeignKey(name = "fk_order_item_order_cd_order"))
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_product_sku", foreignKey = @ForeignKey(name = "fk_order_item_product_sku_cd_product_sku"))
    private ProductSku productSku;
}

