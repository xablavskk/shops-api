package com.xvlkk.sp.shops.repository;

import com.xvlkk.sp.shops.model.ProductSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSkuRepository extends JpaRepository<ProductSku, Long> {
    List<ProductSku> findByProductCdProduct(Long cdProduct);
}
