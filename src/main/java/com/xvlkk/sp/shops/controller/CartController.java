package com.xvlkk.sp.shops.controller;

import com.xvlkk.sp.shops.dto.CartDTO;
import com.xvlkk.sp.shops.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart/v1")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDTO> create(@RequestBody CartDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.create(dto));
    }

    @GetMapping("user/{cdUser}")
    public ResponseEntity<CartDTO> findByUser(@PathVariable Long cdUser) {
        return ResponseEntity.ok(cartService.findByUser(cdUser));
    }

    @GetMapping("session/{sessionId}")
    public ResponseEntity<CartDTO> findBySession(@PathVariable String sessionId) {
        return ResponseEntity.ok(cartService.findBySession(sessionId));
    }

    @PostMapping("{cartId}/items/{skuId}")
    public ResponseEntity<CartDTO> addItem(@PathVariable Long cartId, @PathVariable Long skuId,
                                           @RequestParam int qty) {
        return ResponseEntity.ok(cartService.addItem(cartId, skuId, qty));
    }

    @PutMapping("{cartId}/items/{skuId}")
    public ResponseEntity<CartDTO> updateItem(@PathVariable Long cartId, @PathVariable Long skuId,
                                              @RequestParam int qty) {
        return ResponseEntity.ok(cartService.updateItemQty(cartId, skuId, qty));
    }

    @DeleteMapping("{cartId}/items/{skuId}")
    public ResponseEntity<CartDTO> removeItem(@PathVariable Long cartId, @PathVariable Long skuId) {
        return ResponseEntity.ok(cartService.removeItem(cartId, skuId));
    }

    @DeleteMapping("{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
