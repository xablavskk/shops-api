package com.xvlkk.sp.shops.mapper;

import com.xvlkk.sp.shops.dto.CouponDTO;
import com.xvlkk.sp.shops.model.Coupon;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CouponMapper {
    Coupon toEntity(CouponDTO dto);
    CouponDTO toDto(Coupon coupon);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Coupon partialUpdate(CouponDTO dto, @MappingTarget Coupon coupon);
}
