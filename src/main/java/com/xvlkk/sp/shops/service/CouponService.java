package com.xvlkk.sp.shops.service;

import com.xvlkk.sp.shops.dto.CouponDTO;
import com.xvlkk.sp.shops.enums.CouponTypeEnum;
import com.xvlkk.sp.shops.exceptions.BusinessException;
import com.xvlkk.sp.shops.exceptions.ResourceNotFoundException;
import com.xvlkk.sp.shops.mapper.CouponMapper;
import com.xvlkk.sp.shops.model.Coupon;
import com.xvlkk.sp.shops.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public List<CouponDTO> findAll() {
        return couponRepository.findAll().stream().map(couponMapper::toDto).toList();
    }

    public CouponDTO findById(Long id) {
        return couponMapper.toDto(findOrThrow(id));
    }

    public CouponDTO create(CouponDTO dto) {
        return couponMapper.toDto(couponRepository.save(couponMapper.toEntity(dto)));
    }

    public CouponDTO update(Long id, CouponDTO dto) {
        Coupon existing = findOrThrow(id);
        couponMapper.partialUpdate(dto, existing);
        return couponMapper.toDto(couponRepository.save(existing));
    }

    public void delete(Long id) {
        couponRepository.deleteById(id);
    }

    @Transactional
    public BigDecimal apply(String code, BigDecimal totalAmount) {
        Coupon coupon = couponRepository.findByDsCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found: " + code));

        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(coupon.getDtValidFrom()) || (coupon.getDtValidTo() != null && now.isAfter(coupon.getDtValidTo()))) {
            throw new BusinessException("Coupon is not valid at this time");
        }
        if (coupon.getQtMaxUse() != null && coupon.getQtUse() >= coupon.getQtMaxUse()) {
            throw new BusinessException("Coupon usage limit reached");
        }

        coupon.setQtUse(coupon.getQtUse() + 1);
        couponRepository.save(coupon);

        if (CouponTypeEnum.PERCENTAGE.getTpCoupon().equals(coupon.getTpCoupon())) {
            BigDecimal discount = totalAmount.multiply(coupon.getVlCoupon()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            return totalAmount.subtract(discount);
        } else {
            return totalAmount.subtract(coupon.getVlCoupon()).max(BigDecimal.ZERO);
        }
    }

    private Coupon findOrThrow(Long id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found: " + id));
    }
}
