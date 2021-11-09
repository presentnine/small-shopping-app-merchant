package com.sososhopping.merchant.account.signup.dto;

import com.google.gson.annotations.SerializedName;

public class EmailDupCheckRequestDto {

    @SerializedName("email")
    String email;

    public EmailDupCheckRequestDto(String email) {
        this.email = email;
    }
}
