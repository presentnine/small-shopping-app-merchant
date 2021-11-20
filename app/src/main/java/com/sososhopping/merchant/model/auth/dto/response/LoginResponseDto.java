package com.sososhopping.merchant.model.auth.dto.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponseDto {

    @SerializedName("token")
    String token;

    public String getToken() {
        return token;
    }
}
