package com.xvlkk.sp.shops.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "provider")
@Getter
@Setter
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_provider")
    private Long cdProvider;

    @Column(name = "nm_provider")
    private String nmProvider;
}
