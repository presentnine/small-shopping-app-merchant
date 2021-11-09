package com.sososhopping.merchant.account.login.dto;

import com.google.gson.annotations.SerializedName;

public class LoginResponseDto {

    @SerializedName("token")
    String token;

    public String getToken() {
        return token;
    }
}
