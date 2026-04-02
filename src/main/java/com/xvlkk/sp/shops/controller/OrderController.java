package com.xvlkk.sp.shops.controller;

import com.xvlkk.sp.shops.dto.OrderDTO;
import com.xvlkk.sp.shops.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("user/{cdUser}")
    public ResponseEntity<Page<OrderDTO>> findByUser(@PathVariable Long cdUser, Pageable pageable) {
        return ResponseEntity.ok(orderService.findByUser(cdUser, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(dto));
    }

    @PostMapping("checkout/cart/{cartId}")
    public ResponseEntity<OrderDTO> checkout(@PathVariable Long cartId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.checkoutFromCart(cartId));
    }

    @PatchMapping("{id}/status")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }
}
