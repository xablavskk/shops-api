package com.xvlkk.sp.shops.model;

import com.xvlkk.sp.security.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_cart")
    private Long cdCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_user", foreignKey = @ForeignKey(name = "fk_cart_user_cd_user"))
    private User user;

    @Column(unique = true, name = "ds_session_id")
    private String dsSessionId;

    @Column(name = "dt_updated_at")
    @UpdateTimestamp
    private LocalDateTime dtUpdatedAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

}

