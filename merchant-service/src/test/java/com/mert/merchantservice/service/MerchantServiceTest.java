package com.mert.merchantservice.service;

import com.mert.merchantservice.exception.MerchantNotFoundException;
import com.mert.merchantservice.model.Merchant;
import com.mert.merchantservice.repository.MerchantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MerchantServiceTest {
    @Mock
    private MerchantRepository merchantRepository;

    @InjectMocks
    private MerchantService merchantService;

    @Test
    void testMerchantService_validIdProvided_shouldDeleteMerchant() {
        UUID id = UUID.randomUUID();

        Merchant merchant = new Merchant();
        merchant.setId(id);

        when(merchantRepository.findById(id)).thenReturn(Optional.of(merchant));
        merchantService.deleteMerchant(id);
        verify(merchantRepository).delete(merchant);
    }

    @Test
    void testMerchantService_invalidIdProvided_shouldThrowMerchantNotFoundException() {
        UUID id = UUID.randomUUID();

        when(merchantRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(
                MerchantNotFoundException.class,
                () -> merchantService.deleteMerchant(id)
        );
        verify(merchantRepository, never()).delete(any());
    }
}
