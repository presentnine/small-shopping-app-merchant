package com.sososhopping.merchant.account.login.service;

import com.sososhopping.merchant.account.login.dto.LoginRequestDto;
import com.sososhopping.merchant.account.login.dto.LoginResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("auth/login")
    Call<LoginResponseDto> requestLogin(@Body LoginRequestDto dto);
}
