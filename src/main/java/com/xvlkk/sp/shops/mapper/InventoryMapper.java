package com.xvlkk.sp.shops.mapper;

import com.xvlkk.sp.shops.dto.InventoryDTO;
import com.xvlkk.sp.shops.model.Inventory;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryMapper {
    Inventory toEntity(InventoryDTO dto);
    InventoryDTO toDto(Inventory inventory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Inventory partialUpdate(InventoryDTO dto, @MappingTarget Inventory inventory);
}
