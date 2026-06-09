package com.mert.merchantservice.dto;

import com.mert.merchantservice.dto.validators.CreateMerchantValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MerchantRequestDTO {
    @NotBlank(message = "Merchant name is required")
    @Size(max = 100, message = "Merchant name cannot exceed 100 characters")
    private String merchantName;

    @NotBlank(message = "Store name is required")
    private String storeName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(
            groups = CreateMerchantValidationGroup.class,
            message = "Registered date is required"
    )
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "Registered date must be in yyyy-MM-dd format"
    )
    private String registeredDate;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone Number is required")
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must contain 10 to 15 digits"
    )
    private String phoneNumber;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
