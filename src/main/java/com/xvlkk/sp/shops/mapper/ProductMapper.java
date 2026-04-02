package com.xvlkk.sp.shops.mapper;

import com.xvlkk.sp.shops.dto.ProductDTO;
import com.xvlkk.sp.shops.model.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toEntity(ProductDTO dto);
    ProductDTO toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDTO dto, @MappingTarget Product product);
}
