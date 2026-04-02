package com.xvlkk.sp.shops.service;

import com.xvlkk.sp.shops.dto.InventoryDTO;
import com.xvlkk.sp.shops.exceptions.BusinessException;
import com.xvlkk.sp.shops.exceptions.ResourceNotFoundException;
import com.xvlkk.sp.shops.mapper.InventoryMapper;
import com.xvlkk.sp.shops.model.Inventory;
import com.xvlkk.sp.shops.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryDTO findBySkuId(Long skuId) {
        return inventoryMapper.toDto(inventoryRepository.findByProductSkuCdProductSku(skuId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for SKU: " + skuId)));
    }

    public InventoryDTO save(InventoryDTO dto) {
        return inventoryMapper.toDto(inventoryRepository.save(inventoryMapper.toEntity(dto)));
    }

    @Transactional
    public void reserveStock(Long skuId, int qty) {
        int updated = inventoryRepository.reserveStock(skuId, qty);
        if (updated == 0) {
            throw new BusinessException("Insufficient stock for SKU: " + skuId);
        }
    }

    @Transactional
    public void releaseReservation(Long skuId, int qty) {
        inventoryRepository.releaseReservation(skuId, qty);
    }

    public InventoryDTO update(Long id, InventoryDTO dto) {
        Inventory existing = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found: " + id));
        inventoryMapper.partialUpdate(dto, existing);
        return inventoryMapper.toDto(inventoryRepository.save(existing));
    }
}
