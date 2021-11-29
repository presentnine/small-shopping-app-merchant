package com.sososhopping.merchant.model.coupon.repository;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sososhopping.merchant.model.coupon.dto.request.CouponRegisterRequestDto;
import com.sososhopping.merchant.model.coupon.dto.response.CouponListResponseDto;
import com.sososhopping.merchant.model.coupon.service.CouponService;
import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CouponRepository {

    private static CouponRepository instance;
    private final CouponService service;

    private CouponRepository() {
        this.service = ApiServiceFactory.createService(CouponService.class);
    }

    public synchronized static CouponRepository getInstance() {
        if (instance == null) {
            instance = new CouponRepository();
        }
        return instance;
    }

    public void requestCouponList(String token, int storeId, Consumer<CouponListResponseDto> onSuccess, Runnable onError) {
        service.requestCouponList(token, storeId).enqueue(new Callback<CouponListResponseDto>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CouponListResponseDto> call, Response<CouponListResponseDto> response) {
                System.out.println(response.code());
                if(response.code() == 200) onSuccess.accept(response.body());
                else onError.run();
            }

            @Override
            public void onFailure(Call<CouponListResponseDto> call, Throwable t) {
                onError.run();
            }
        });
    }

    public void requestRegister(String token, int storeId, CouponRegisterRequestDto dto) {
        service.requestCouponRegister(token, storeId, dto).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                ;
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                ;
            }
        });
    }
}
