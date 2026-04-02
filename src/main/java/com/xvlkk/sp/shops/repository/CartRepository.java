package com.xvlkk.sp.shops.repository;

import com.xvlkk.sp.shops.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserCdUser(Long cdUser);
    Optional<Cart> findByDsSessionId(String sessionId);
}
