package com.xvlkk.sp.shops.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "secuarity_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_user")
    private Long cdUser;

    @Column(name = "nm_user")
    private String nmUser;

    @Column(name = "nm_email")
    private String nmEmail;

    @Column(name = "nm_login")
    private String nmLogin;

    @Column(name = "nm_password")
    private String nmPassword;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="users_profiles",
            joinColumns = @JoinColumn(name = "cd_user"),
            inverseJoinColumns = @JoinColumn(name="cd_profile"))
    private List<Profile> profiles;

}
