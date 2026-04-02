package com.xvlkk.sp.shops.repository;

import com.xvlkk.sp.shops.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductSkuCdProductSku(Long cdProductSku);

    @Modifying
    @Query("UPDATE Inventory i SET i.qtReserved = i.qtReserved + :qty, i.qtAvailable = i.qtAvailable - :qty WHERE i.productSku.cdProductSku = :skuId AND i.qtAvailable >= :qty")
    int reserveStock(Long skuId, int qty);

    @Modifying
    @Query("UPDATE Inventory i SET i.qtReserved = i.qtReserved - :qty WHERE i.productSku.cdProductSku = :skuId AND i.qtReserved >= :qty")
    int releaseReservation(Long skuId, int qty);
}
