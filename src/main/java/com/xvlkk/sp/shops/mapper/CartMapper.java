package com.xvlkk.sp.shops.mapper;

import com.xvlkk.sp.shops.dto.CartDTO;
import com.xvlkk.sp.shops.model.Cart;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartMapper {
    Cart toEntity(CartDTO dto);
    CartDTO toDto(Cart cart);
}
