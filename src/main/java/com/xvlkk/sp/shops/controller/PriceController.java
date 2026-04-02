package com.xvlkk.sp.shops.controller;

import com.xvlkk.sp.shops.dto.PriceDTO;
import com.xvlkk.sp.shops.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("price/v1")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping("sku/{skuId}/effective")
    public ResponseEntity<BigDecimal> getEffectivePrice(@PathVariable Long skuId) {
        return ResponseEntity.ok(priceService.getEffectivePrice(skuId));
    }

    @PostMapping
    public ResponseEntity<PriceDTO> create(@RequestBody PriceDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(priceService.create(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<PriceDTO> update(@PathVariable Long id, @RequestBody PriceDTO dto) {
        return ResponseEntity.ok(priceService.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        priceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
