package com.xvlkk.sp.shops.controller;

import com.xvlkk.sp.shops.dto.CouponDTO;
import com.xvlkk.sp.shops.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("coupon/v1")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public ResponseEntity<List<CouponDTO>> findAll() {
        return ResponseEntity.ok(couponService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<CouponDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(couponService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CouponDTO> create(@RequestBody CouponDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.create(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<CouponDTO> update(@PathVariable Long id, @RequestBody CouponDTO dto) {
        return ResponseEntity.ok(couponService.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        couponService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("apply")
    public ResponseEntity<BigDecimal> apply(@RequestParam String code, @RequestParam BigDecimal totalAmount) {
        return ResponseEntity.ok(couponService.apply(code, totalAmount));
    }
}
