package com.xvlkk.sp.shops.repository;

import com.xvlkk.sp.shops.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByOrderCdOrder(Long cdOrder);
    Optional<Shipment> findByDsTrackingNumber(String trackingNumber);
}
