package com.xvlkk.sp.shops.mapper;

import com.xvlkk.sp.shops.dto.ShipmentDTO;
import com.xvlkk.sp.shops.model.Shipment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShipmentMapper {

    @Mapping(source = "id", target = "cdShipment")
    Shipment toEntity(ShipmentDTO dto);

    @Mapping(source = "cdShipment", target = "id")
    ShipmentDTO toDto(Shipment shipment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "id", target = "cdShipment")
    Shipment partialUpdate(ShipmentDTO dto, @MappingTarget Shipment shipment);
}
