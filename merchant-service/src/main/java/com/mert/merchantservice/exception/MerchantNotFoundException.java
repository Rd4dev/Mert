package com.mert.merchantservice.exception;

public class MerchantNotFoundException extends RuntimeException {
    public MerchantNotFoundException(String message) {
        super(message);
    }
}
