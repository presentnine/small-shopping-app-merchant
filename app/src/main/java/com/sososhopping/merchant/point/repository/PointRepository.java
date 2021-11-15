package com.sososhopping.merchant.point.repository;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sososhopping.merchant.common.retrofit.ApiServiceFactory;
import com.sososhopping.merchant.point.dto.PointRuleRequestDto;
import com.sososhopping.merchant.point.dto.PointRuleResponseDto;
import com.sososhopping.merchant.point.service.PointService;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PointRepository {
    private static PointRepository instance;
    private final PointService shopListService;

    private PointRepository() {
        this.shopListService = ApiServiceFactory.createService(PointService.class);
    }

    public static synchronized PointRepository getInstance() {
        if(instance == null) {
            instance = new PointRepository();
        }

        return instance;
    }

    public void requestPointRule(String token, int storeId, Consumer<PointRuleResponseDto> onSuccess) {
        shopListService.requestPointRule(token, storeId).enqueue(new Callback<PointRuleResponseDto>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<PointRuleResponseDto> call, Response<PointRuleResponseDto> response) {
                onSuccess.accept(response.body());
            }

            @Override
            public void onFailure(Call<PointRuleResponseDto> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void requestPointRuleUpdate(String token, int storeId, PointRuleRequestDto dto) {
        shopListService.requestPointRuleUpdate(token, storeId, dto).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
