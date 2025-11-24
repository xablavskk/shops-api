package com.xvlkk.sp.shops.model;

import com.xvlkk.sp.security.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_order")
    private Long cdOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_user", foreignKey = @ForeignKey(name = "fk_order_user_cd_user"))
    private User user;

    @Column(unique = true, name = "ds_order_number")
    private String dsOrderNumber;

    @Column(name = "qt_total_amount")
    private BigDecimal qtTotalAmount;

    @Column(name = "st_order")
    private String status; // USE ORDER STATUS ENUM CLASS

    @Column(name = "dt_created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "dt_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_user_created_at", foreignKey = @ForeignKey(name = "order_user_cd_user_created_at"))
    private User userCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_user_updated_at", foreignKey = @ForeignKey(name = "order_user_cd_user_updated_at"))
    private User userUpdated;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Shipment> shipments = new ArrayList<>();
}
