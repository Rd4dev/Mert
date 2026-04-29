package com.mert.merchantservice.controller;

import com.mert.merchantservice.dto.MerchantRequestDTO;
import com.mert.merchantservice.dto.MerchantResponseDTO;
import com.mert.merchantservice.dto.validators.CreateMerchantValidationGroup;
import com.mert.merchantservice.service.MerchantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/merchants")
@Tag(name = "Merchant", description = "API for managing merchants")
public class MerchantController {
    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping
    @Operation(summary = "Get Merchants")
    public ResponseEntity<List<MerchantResponseDTO>> getAllMerchants() {
        List<MerchantResponseDTO> merchants = merchantService.getAllMerchants();
        return ResponseEntity.ok().body(merchants);
    }

    @PostMapping
    @Operation(summary = "Create a new Merchant")
    public ResponseEntity<MerchantResponseDTO> createMerchant(@Validated({Default.class, CreateMerchantValidationGroup.class}) @RequestBody MerchantRequestDTO merchantRequestDTO) {
        MerchantResponseDTO merchantResponseDTO = merchantService.createMerchant(merchantRequestDTO);
        return ResponseEntity.ok().body(merchantResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Merchant")
    public ResponseEntity<MerchantResponseDTO> updateMerchant(@PathVariable UUID id, @Validated({Default.class}) @RequestBody MerchantRequestDTO merchantRequestDTO) {
        MerchantResponseDTO merchantResponseDTO = merchantService.updateMerchant(id, merchantRequestDTO);
        return ResponseEntity.ok().body(merchantResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Merchant")
    public ResponseEntity<Void> deleteMerchant(@PathVariable UUID id) {
        merchantService.deleMerchant(id);
        return ResponseEntity.noContent().build();
    }
}
