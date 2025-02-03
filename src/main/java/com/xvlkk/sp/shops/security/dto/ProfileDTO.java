package com.xvlkk.sp.shops.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileDTO {
    private Long cdProfile;

    private String profile;

    private List<RoleDTO> roles;
}
