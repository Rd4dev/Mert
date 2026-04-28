package com.mert.merchantservice.service;

import com.mert.merchantservice.dto.MerchantRequestDTO;
import com.mert.merchantservice.dto.MerchantResponseDTO;
import com.mert.merchantservice.exception.EmailAlreadyExistsException;
import com.mert.merchantservice.mapper.MerchantMapper;
import com.mert.merchantservice.model.Merchant;
import com.mert.merchantservice.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {
    private MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public List<MerchantResponseDTO> getAllMerchants() {
        List<Merchant> merchants = merchantRepository.findAll();
        List<MerchantResponseDTO> merchantResponseDTOS = merchants.stream().map(merchant -> MerchantMapper.toMerchantResponseDTO(merchant)).toList();
        return merchantResponseDTOS;
    }

    public MerchantResponseDTO createMerchant(MerchantRequestDTO merchantRequestDTO) {
        if(merchantRepository.existsByEmail(merchantRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A merchant with this email already exists "
                    + merchantRequestDTO.getEmail());
        }
        Merchant newMerchant = merchantRepository.save(MerchantMapper.toMerchant(merchantRequestDTO));
        return MerchantMapper.toMerchantResponseDTO(newMerchant);
    }
}
