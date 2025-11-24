package com.xvlkk.sp.shops.dto;

import com.xvlkk.sp.security.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.Payment}
 */
@AllArgsConstructor
@Getter
public class PaymentDTO implements Serializable {
    private final Long cdPayment;
    private final BigDecimal qtAmount;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final OrderDTO order;
    private final ProviderDTO provider;
    private final UserDTO userCreated;
    private final UserDTO userUpdated;
}