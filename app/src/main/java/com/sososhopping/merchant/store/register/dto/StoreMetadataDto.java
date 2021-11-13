package com.sososhopping.merchant.store.register.dto;

public class StoreMetadataDto {
    String businessNumber;
    String representativeName;
    String businessName;
    String openingDate;

    public StoreMetadataDto(String businessNumber, String representativeName, String businessName, String openingDate) {
        this.businessNumber = businessNumber;
        this.representativeName = representativeName;
        this.businessName = businessName;
        this.openingDate = openingDate;
    }
}
