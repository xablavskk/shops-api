package com.xvlkk.sp.shops.controller;

import com.xvlkk.sp.shops.dto.PaymentDTO;
import com.xvlkk.sp.shops.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment/v1")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("order/{cdOrder}")
    public ResponseEntity<List<PaymentDTO>> findByOrder(@PathVariable Long cdOrder) {
        return ResponseEntity.ok(paymentService.findByOrder(cdOrder));
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PostMapping("order/{cdOrder}")
    public ResponseEntity<PaymentDTO> create(@PathVariable Long cdOrder, @RequestBody PaymentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(cdOrder, dto));
    }

    @PatchMapping("{id}/approve")
    public ResponseEntity<PaymentDTO> approve(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.approve(id));
    }

    @PatchMapping("{id}/decline")
    public ResponseEntity<PaymentDTO> decline(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.decline(id));
    }

    @PatchMapping("{id}/refund")
    public ResponseEntity<PaymentDTO> refund(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.refund(id));
    }
}
