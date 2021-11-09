package com.sososhopping.merchant.account.signup.service;

import com.sososhopping.merchant.account.signup.dto.EmailDupCheckRequestDto;
import com.sososhopping.merchant.account.signup.dto.SignupRequestDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignupService {

    @POST("auth/signup")
    Call<Void> requestSignup(@Body SignupRequestDto dto);

    @POST("auth/signup/validation")
    Call<Void> requestEmailDuplicationCheck(@Body EmailDupCheckRequestDto dto);
}
