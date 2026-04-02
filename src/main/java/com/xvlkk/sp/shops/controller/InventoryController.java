package com.xvlkk.sp.shops.controller;

import com.xvlkk.sp.shops.dto.InventoryDTO;
import com.xvlkk.sp.shops.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inventory/v1")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("sku/{skuId}")
    public ResponseEntity<InventoryDTO> findBySkuId(@PathVariable Long skuId) {
        return ResponseEntity.ok(inventoryService.findBySkuId(skuId));
    }

    @PutMapping("{id}")
    public ResponseEntity<InventoryDTO> update(@PathVariable Long id, @RequestBody InventoryDTO dto) {
        return ResponseEntity.ok(inventoryService.update(id, dto));
    }
}
