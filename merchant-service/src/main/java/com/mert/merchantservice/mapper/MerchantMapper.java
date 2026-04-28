package com.mert.merchantservice.mapper;

import com.mert.merchantservice.dto.MerchantResponseDTO;
import com.mert.merchantservice.model.Merchant;

public class MerchantMapper {
    public static MerchantResponseDTO toMerchantResponseDTO(Merchant merchant) {
        MerchantResponseDTO merchantResponseDTO = new MerchantResponseDTO();
        merchantResponseDTO.setId(merchant.getId().toString());
        merchantResponseDTO.setMerchantName(merchant.getMerchantName());
        merchantResponseDTO.setStoreName(merchant.getStoreName());
        merchantResponseDTO.setAddress(merchant.getAddress());
        merchantResponseDTO.setPhoneNumber(merchant.getPhoneNumber());

        return merchantResponseDTO;
    }
}
