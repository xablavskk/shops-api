package com.xvlkk.sp.shops.repository;

import com.xvlkk.sp.shops.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByDsCode(String dsCode);
}
