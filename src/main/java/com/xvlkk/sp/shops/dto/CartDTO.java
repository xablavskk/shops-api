package com.xvlkk.sp.shops.dto;

import com.xvlkk.sp.security.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.Cart}
 */
@Getter
@Setter
public class CartDTO implements Serializable {
    private Long cdCart;
    private String dsSessionId;
    private LocalDateTime dtUpdatedAt;

    private UserDTO user;

    private List<CartItemDTO> items;
}