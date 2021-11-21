package com.sososhopping.merchant.model.point.repository;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sososhopping.merchant.model.point.dto.request.PointRuleRequestDto;
import com.sososhopping.merchant.model.point.dto.response.PointRuleResponseDto;
import com.sososhopping.merchant.model.point.service.PointService;
import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PointRepository {

    private static PointRepository instance;
    private final PointService service;

    private PointRepository() {
        this.service = ApiServiceFactory.createService(PointService.class);
    }

    public static synchronized PointRepository getInstance() {
        if(instance == null) {
            instance = new PointRepository();
        }

        return instance;
    }

    public void requestPointRule(String token, int storeId, Consumer<PointRuleResponseDto> onSuccess, Runnable onError) {
        service.requestPointRule(token, storeId).enqueue(new Callback<PointRuleResponseDto>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<PointRuleResponseDto> call, Response<PointRuleResponseDto> response) {
                if (response.code() == 200) {
                    onSuccess.accept(response.body());
                } else {
                    onError.run();
                }
            }

            @Override
            public void onFailure(Call<PointRuleResponseDto> call, Throwable t) {
                onError.run();
            }
        });
    }

    public void requestPointRuleUpdate(String token, int storeId, PointRuleRequestDto dto, Runnable onError) {
        service.requestPointRuleUpdate(token, storeId, dto).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onError.run();
            }
        });
    }
}
