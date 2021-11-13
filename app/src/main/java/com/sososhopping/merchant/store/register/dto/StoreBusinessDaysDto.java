package com.sososhopping.merchant.store.register.dto;

public class StoreBusinessDaysDto {

    String day;
    Boolean isOpen;
    String openTime;
    String closeTime;

    public StoreBusinessDaysDto(String day, Boolean isOpen, String openTime, String closeTime) {
        this.day = day;
        this.isOpen = isOpen;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
