package com.xvlkk.sp.shops.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.Category}
 */
@Getter
@Setter
public class CategoryDTO implements Serializable {
    private Long cdCategory;

    @NotBlank(message = "Category name is required")
    @Size(max = 100)
    private String nmCategory;

    @NotBlank(message = "Slug is required")
    @Size(max = 100)
    private String nmSlug;

    private CategoryDTO categoryParent;
    private List<CategoryDTO> categoryChilds;
}
