package com.xvlkk.sp.shops.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_inventory")
    private Long cdInventory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_product_sku", foreignKey = @ForeignKey(name = "fk_inventory_product_sku_cd_product_sku"))
    private ProductSku productSku;

    @Column(name = "qt_available")
    private Integer qtAvailable;

    @Column(name = "qt_reserved")
    private Integer qtReserved;
}

