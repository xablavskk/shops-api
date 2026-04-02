package com.xvlkk.sp.shops.repository;

import com.xvlkk.sp.shops.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByStActiveTrue(Pageable pageable);
    Page<Product> findByCategoryCdCategory(Long cdCategory, Pageable pageable);
    Page<Product> findByStActiveTrueAndNmProductContainingIgnoreCase(String name, Pageable pageable);
}
