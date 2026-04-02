package com.xvlkk.sp.shops.repository;

import com.xvlkk.sp.shops.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNmSlug(String nmSlug);
    boolean existsByNmSlug(String nmSlug);
}
