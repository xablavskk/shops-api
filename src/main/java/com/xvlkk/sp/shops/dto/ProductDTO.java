package com.xvlkk.sp.shops.dto;

import com.xvlkk.sp.security.dto.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Product name is required")
    @Size(max = 200)
    private String nmProduct;

    @Length(max = 1800)
    private String dsProduct;

    @Size(max = 100)
    private String dsBrand;

    private Boolean stActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotNull(message = "Category is required")
    private CategoryDTO category;

    private UserDTO userCreated;
    private UserDTO userUpdated;
    private List<ProductSkuDTO> skus;
}
