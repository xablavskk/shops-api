package com.xvlkk.sp.shops.controller;

import com.xvlkk.sp.shops.dto.ShipmentDTO;
import com.xvlkk.sp.shops.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shipment/v1")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @GetMapping("order/{cdOrder}")
    public ResponseEntity<List<ShipmentDTO>> findByOrder(@PathVariable Long cdOrder) {
        return ResponseEntity.ok(shipmentService.findByOrder(cdOrder));
    }

    @GetMapping("{id}")
    public ResponseEntity<ShipmentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(shipmentService.findById(id));
    }

    @PostMapping("order/{cdOrder}")
    public ResponseEntity<ShipmentDTO> create(@PathVariable Long cdOrder, @RequestBody ShipmentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shipmentService.create(cdOrder, dto));
    }

    @PatchMapping("{id}/ship")
    public ResponseEntity<ShipmentDTO> ship(@PathVariable Long id) {
        return ResponseEntity.ok(shipmentService.ship(id));
    }

    @PatchMapping("{id}/deliver")
    public ResponseEntity<ShipmentDTO> deliver(@PathVariable Long id) {
        return ResponseEntity.ok(shipmentService.deliver(id));
    }
}
