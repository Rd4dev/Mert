package com.mert.merchantservice.service;

import com.mert.merchantservice.dto.MerchantRequestDTO;
import com.mert.merchantservice.dto.MerchantResponseDTO;
import com.mert.merchantservice.exception.EmailAlreadyExistsException;
import com.mert.merchantservice.exception.MerchantNotFoundException;
import com.mert.merchantservice.grpc.BillingServiceGrpcClient;
import com.mert.merchantservice.kafka.KafkaProducer;
import com.mert.merchantservice.mapper.MerchantMapper;
import com.mert.merchantservice.model.Merchant;
import com.mert.merchantservice.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MerchantService {
    private MerchantRepository merchantRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    public MerchantService(
            MerchantRepository merchantRepository,
            BillingServiceGrpcClient billingServiceGrpcClient,
            KafkaProducer kafkaProducer
    ) {
        this.merchantRepository = merchantRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
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
        billingServiceGrpcClient.createBillingAccount(
                newMerchant.getId().toString(),
                newMerchant.getMerchantName(),
                newMerchant.getStoreName(),
                newMerchant.getEmail()
        );

        kafkaProducer.sendEvent(newMerchant);

        return MerchantMapper.toMerchantResponseDTO(newMerchant);
    }

    public MerchantResponseDTO updateMerchant(UUID id, MerchantRequestDTO merchantRequestDTO) {
        if(merchantRepository.existsByEmailAndIdNot(merchantRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A merchant with this email already exists "
                    + merchantRequestDTO.getEmail());
        }
        Merchant merchant = merchantRepository.findById(id)
                .orElseThrow(() -> new MerchantNotFoundException("Merchant not found with ID: " + id));

        merchant.setMerchantName(merchantRequestDTO.getMerchantName());
        merchant.setStoreName(merchantRequestDTO.getStoreName());
        merchant.setEmail(merchantRequestDTO.getEmail());
        merchant.setAddress(merchantRequestDTO.getAddress());
        merchant.setPhoneNumber(merchantRequestDTO.getPhoneNumber());

        Merchant updatedMerchant = merchantRepository.save(merchant);
        return MerchantMapper.toMerchantResponseDTO(updatedMerchant);
    }

    public void deleMerchant(UUID id) {
        merchantRepository.deleteById(id);
    }
}
