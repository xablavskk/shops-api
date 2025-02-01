package com.xvlkk.sp.shops.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "security_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_role")
    private Long cdRole;

    @Column(name = "nm_role")
    private String role;
}
