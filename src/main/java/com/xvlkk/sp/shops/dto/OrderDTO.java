package com.xvlkk.sp.shops.dto;

import com.xvlkk.sp.security.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.Order}
 */
@Getter
@Setter
public class OrderDTO implements Serializable {
    private Long cdOrder;
    private UserDTO user;
    private String dsOrderNumber;
    private BigDecimal qtTotalAmount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDTO userCreated;
    private UserDTO userUpdated;
    private List<OrderItemDTO> items;
    private List<PaymentDTO> payments;
    private List<ShipmentDTO> shipments;
}