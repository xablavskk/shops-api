package com.xvlkk.sp.shops.dto;

import com.xvlkk.sp.security.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.Payment}
 */
@Getter
@Setter
public class PaymentDTO implements Serializable {
    private Long cdPayment;
    private BigDecimal qtAmount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private OrderDTO order;
    private ProviderDTO provider;
    private UserDTO userCreated;
    private UserDTO userUpdated;
}
