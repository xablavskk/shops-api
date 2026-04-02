package com.xvlkk.sp.shops.mapper;

import com.xvlkk.sp.shops.dto.PriceDTO;
import com.xvlkk.sp.shops.model.Price;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {
    Price toEntity(PriceDTO dto);
    PriceDTO toDto(Price price);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Price partialUpdate(PriceDTO dto, @MappingTarget Price price);
}
