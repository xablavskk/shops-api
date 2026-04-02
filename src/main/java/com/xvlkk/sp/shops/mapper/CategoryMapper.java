package com.xvlkk.sp.shops.mapper;

import com.xvlkk.sp.shops.dto.CategoryDTO;
import com.xvlkk.sp.shops.model.Category;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toEntity(CategoryDTO dto);
    CategoryDTO toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryDTO dto, @MappingTarget Category category);
}
