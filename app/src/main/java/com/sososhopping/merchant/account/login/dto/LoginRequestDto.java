package com.sososhopping.merchant.account.login.dto;

import com.google.gson.annotations.SerializedName;

public class LoginRequestDto {
    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
