package com.mert.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import merchant.events.MerchantEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "merchant", groupId = "test-group-1")
    public void consumeEvent(byte[] event) {
        try {
            MerchantEvent merchantEvent = MerchantEvent.parseFrom(event);
            // analytics logic

            log.info("Received Merchant Event: [MerchantId={}, MerchantName={}, StoreName={}, MerchantEmail={}]",
                    merchantEvent.getMerchantId(),
                    merchantEvent.getMerchantName(),
                    merchantEvent.getStoreName(),
                    merchantEvent.getEmail());
        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}", e.getMessage());
        }
    }
}
