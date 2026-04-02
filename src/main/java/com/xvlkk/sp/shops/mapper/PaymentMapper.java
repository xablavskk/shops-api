package com.xvlkk.sp.shops.mapper;

import com.xvlkk.sp.shops.dto.PaymentDTO;
import com.xvlkk.sp.shops.model.Payment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    Payment toEntity(PaymentDTO dto);
    PaymentDTO toDto(Payment payment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Payment partialUpdate(PaymentDTO dto, @MappingTarget Payment payment);
}
