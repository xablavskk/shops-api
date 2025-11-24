package com.xvlkk.sp.shops.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_cart_item")
    private Long cdCartItem;

    @Column(name = "qt_cart_item")
    private Integer qtCartItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_cart")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_product_sku")
    private ProductSku productSku;
}

