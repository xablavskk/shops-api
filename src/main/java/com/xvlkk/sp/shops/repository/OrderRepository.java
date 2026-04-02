package com.xvlkk.sp.shops.repository;

import com.xvlkk.sp.shops.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUserCdUser(Long cdUser, Pageable pageable);
    Optional<Order> findByDsOrderNumber(String dsOrderNumber);
}
