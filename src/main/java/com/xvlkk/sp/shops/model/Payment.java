package com.xvlkk.sp.shops.model;

import com.xvlkk.sp.security.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_payment")
    private Long cdPayment;

    @Column(name = "qt_amount")
    private BigDecimal qtAmount;

    @Column(name = "st_payment")
    private String status; // USE THE ENUM PAYMENT STATUS

    @Column(name = "dt_created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "dt_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_order")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_provider", foreignKey = @ForeignKey(name = "fk_payment_provider_cd_provider"))
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_user_created_at", foreignKey = @ForeignKey(name = "fk_payment_user_cd_user_created_at"))
    private User userCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_user_updated_at", foreignKey = @ForeignKey(name = "fk_payment_user_cd_user_updated_at"))
    private User userUpdated;
}
