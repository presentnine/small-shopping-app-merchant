package com.sososhopping.merchant.model.accounting.dto.request;

import com.google.gson.annotations.SerializedName;

public class AccountingRegisterRequestDto {

    @SerializedName("date")
    String date;
    @SerializedName("amount")
    int amount;
    @SerializedName("description")
    String description;

    public AccountingRegisterRequestDto(String date, int amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }
}
