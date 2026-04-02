package com.xvlkk.sp.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long cdUser;

    private String nmUser;

    private String nmEmail;

    private String nmLogin;

    private String nmPassword;

    private List<ProfileDTO> profiles;
}
