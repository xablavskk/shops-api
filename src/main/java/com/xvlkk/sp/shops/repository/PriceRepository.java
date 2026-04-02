package com.xvlkk.sp.shops.repository;

import com.xvlkk.sp.shops.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.productSku.cdProductSku = :skuId AND p.stPromotional = true AND p.dtValidFrom <= :now AND (p.dtValidTo IS NULL OR p.dtValidTo >= :now) ORDER BY p.nrAmount ASC")
    Optional<Price> findActivePromotionalPrice(Long skuId, LocalDateTime now);

    @Query("SELECT p FROM Price p WHERE p.productSku.cdProductSku = :skuId AND p.stPromotional = false AND p.dtValidFrom <= :now AND (p.dtValidTo IS NULL OR p.dtValidTo >= :now) ORDER BY p.dtValidFrom DESC")
    Optional<Price> findActiveRegularPrice(Long skuId, LocalDateTime now);
}
