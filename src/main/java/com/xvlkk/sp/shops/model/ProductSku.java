package com.xvlkk.sp.shops.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_sku")
@Getter
@Setter
public class ProductSku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_product_sku")
    private Long cdProductSku;

    @Column(name = "ds_sku")
    private String dsSku;

    @Column(name = "ds_attributes")
    private String attributesJson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_product", foreignKey = @ForeignKey(name = "fk_product_sku_product_cd_product"))
    private Product product;

    @OneToOne(mappedBy = "productSku", cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventory inventory;

    @OneToMany(mappedBy = "productSku", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Price> prices = new ArrayList<>();
}

