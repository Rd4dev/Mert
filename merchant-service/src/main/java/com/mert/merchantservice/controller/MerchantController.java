package com.mert.merchantservice.controller;

import com.mert.merchantservice.dto.MerchantRequestDTO;
import com.mert.merchantservice.dto.MerchantResponseDTO;
import com.mert.merchantservice.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchants")
public class MerchantController {
    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping
    public ResponseEntity<List<MerchantResponseDTO>> getAllMerchants() {
        List<MerchantResponseDTO> merchants = merchantService.getAllMerchants();
        return ResponseEntity.ok().body(merchants);
    }

    @PostMapping
    public ResponseEntity<MerchantResponseDTO> createMerchant(@Valid @RequestBody MerchantRequestDTO merchantRequestDTO) {
        MerchantResponseDTO merchantResponseDTO = merchantService.createMerchant(merchantRequestDTO);
        return ResponseEntity.ok().body(merchantResponseDTO);
    }
}
