package com.sososhopping.merchant.account.login.repository;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sososhopping.merchant.account.login.dto.LoginRequestDto;
import com.sososhopping.merchant.account.login.dto.LoginResponseDto;
import com.sososhopping.merchant.account.login.service.LoginService;
import com.sososhopping.merchant.common.retrofit.ApiServiceFactory;

import java.util.function.BiConsumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static LoginRepository instance;
    private final LoginService loginService;

    private LoginRepository() {
        this.loginService = ApiServiceFactory.createService(LoginService.class);
    }

    public static synchronized LoginRepository getInstance() {
        if(instance == null) {
            instance = new LoginRepository();
        }

        return instance;
    }

    public void requestLogin(LoginRequestDto dto,
                             BiConsumer<LoginRequestDto, LoginResponseDto> onSuccess,
                             Runnable onFailed,
                             Runnable onError) {
        loginService.requestLogin(dto).enqueue(new Callback<LoginResponseDto>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {
                if (response.code() == 200) {
                    onSuccess.accept(dto,response.body());
                } else {
                    onFailed.run();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseDto> call, Throwable t) {
                onError.run();
            }
        });
    }
}
