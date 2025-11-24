package com.xvlkk.sp.shops.dto;

import com.xvlkk.sp.security.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.Product}
 */
@Setter
@Getter
public class ProductDTO implements Serializable {
    private Long cdProduct;
    private String nmProduct;
    @Length(max = 1800)
    private String dsProduct;
    private String dsBrand;
    private Boolean stActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CategoryDTO category;
    private UserDTO userCreated;
    private UserDTO userUpdated;

    private List<ProductSkuDTO> skus;
}