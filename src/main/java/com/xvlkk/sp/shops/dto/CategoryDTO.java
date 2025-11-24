package com.xvlkk.sp.shops.dto;

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
    private String nmCategory;
    private String nmSlug;

    private CategoryDTO categoryParent;

    private List<CategoryDTO> categoryChilds;
}