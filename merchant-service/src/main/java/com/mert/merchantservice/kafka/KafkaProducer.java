package com.mert.merchantservice.kafka;

import com.mert.merchantservice.model.Merchant;
import merchant.events.MerchantEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Merchant merchant) {
        MerchantEvent event = MerchantEvent.newBuilder()
                .setMerchantId(merchant.getId().toString())
                .setMerchantName(merchant.getMerchantName())
                .setStoreName(merchant.getStoreName())
                .setEmail(merchant.getEmail())
                .setEventType("MERCHANT_CREATED")
                .build();

        try {
            kafkaTemplate.send("merchant", event.toByteArray());
        } catch (Exception e) {
            log.error("Error sending MerchantCreated event: {}", event);
        }
    }
}
