package com.xvlkk.sp.shops.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "security_profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_profile")
    private Long cdProfile;

    @Column(name = "nm_profile")
    private String profile;

    @OneToMany
    @JoinColumn(name = "security_profile_cd_role", foreignKey = @ForeignKey(name = "fk_security_profile_cd_role"))
    private List<Role> roles;
}
