package com.xvlkk.sp.shops.repository;

import com.xvlkk.sp.shops.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrderCdOrder(Long cdOrder);
}
