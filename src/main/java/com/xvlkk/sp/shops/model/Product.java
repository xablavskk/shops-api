package com.xvlkk.sp.shops.model;

import com.xvlkk.sp.security.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdProduct;

    @Column(name = "nm_product")
    private String nmProduct;

    @Column(name = "ds_product")
    @Length(max = 1800)
    private String dsProduct;

    @Column(name = "ds_brand")
    private String dsBrand;

    @Column(name = "st_active")
    private Boolean stActive;

    @Column(name = "dt_created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "dt_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_category")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_user_created_at", foreignKey = @ForeignKey(name = "product_user_cd_user_created_at"))
    private User userCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_user_updated_at", foreignKey = @ForeignKey(name = "product_user_cd_user_updated_at"))
    private User userUpdated;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSku> skus = new ArrayList<>();
}
