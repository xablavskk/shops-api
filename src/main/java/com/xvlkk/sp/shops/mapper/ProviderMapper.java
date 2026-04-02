package com.xvlkk.sp.shops.mapper;

import com.xvlkk.sp.shops.dto.ProviderDTO;
import com.xvlkk.sp.shops.model.Provider;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProviderMapper {
    Provider toEntity(ProviderDTO dto);
    ProviderDTO toDto(Provider provider);
}
