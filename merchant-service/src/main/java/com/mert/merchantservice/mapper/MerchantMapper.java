package com.mert.merchantservice.mapper;

import com.mert.merchantservice.dto.MerchantRequestDTO;
import com.mert.merchantservice.dto.MerchantResponseDTO;
import com.mert.merchantservice.model.Merchant;

import java.time.LocalDate;

public class MerchantMapper {
    public static MerchantResponseDTO toMerchantResponseDTO(Merchant merchant) {
        MerchantResponseDTO merchantResponseDTO = new MerchantResponseDTO();
        merchantResponseDTO.setId(merchant.getId().toString());
        merchantResponseDTO.setMerchantName(merchant.getMerchantName());
        merchantResponseDTO.setStoreName(merchant.getStoreName());
        merchantResponseDTO.setAddress(merchant.getAddress());
        merchantResponseDTO.setEmail(merchant.getEmail());
        merchantResponseDTO.setPhoneNumber(merchant.getPhoneNumber());

        return merchantResponseDTO;
    }

    public static Merchant toMerchant(MerchantRequestDTO merchantRequestDTO) {
        Merchant merchant = new Merchant();
        merchant.setMerchantName(merchantRequestDTO.getMerchantName());
        merchant.setStoreName(merchantRequestDTO.getStoreName());
        merchant.setEmail(merchantRequestDTO.getEmail());
        merchant.setAddress(merchantRequestDTO.getAddress());
        merchant.setRegisteredDate(LocalDate.parse(merchantRequestDTO.getRegisteredDate()));
        merchant.setPhoneNumber(merchantRequestDTO.getPhoneNumber());

        return merchant;
    }
}
