package com.sososhopping.merchant.store.manage.repository;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sososhopping.merchant.common.retrofit.ApiServiceFactory;
import com.sososhopping.merchant.store.manage.dto.StoreBusinessStatusDto;
import com.sososhopping.merchant.store.manage.service.StoreManagementService;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreBusinessStatusRepository {
    private static StoreBusinessStatusRepository instance;
    private final StoreManagementService shopListService;

    private StoreBusinessStatusRepository() {
        this.shopListService = ApiServiceFactory.createService(StoreManagementService.class);
    }

    public static synchronized StoreBusinessStatusRepository getInstance() {
        if(instance == null) {
            instance = new StoreBusinessStatusRepository();
        }

        return instance;
    }

    public void requestStoreBusinessStatus(String token, int storeId, Consumer<StoreBusinessStatusDto> consumer) {
        shopListService.requestBusinessStatus(token, storeId).enqueue(new Callback<StoreBusinessStatusDto>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<StoreBusinessStatusDto> call, Response<StoreBusinessStatusDto> response) {
                System.out.println(response.code());
                System.out.println(response.body());
                consumer.accept(response.body());
            }

            @Override
            public void onFailure(Call<StoreBusinessStatusDto> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void requestStoreBusinessStatusUpdate(String token, int storeId, Consumer<StoreBusinessStatusDto> consumer) {
        shopListService.requestBusinessStatusUpdate(token, storeId).enqueue(new Callback<StoreBusinessStatusDto>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<StoreBusinessStatusDto> call, Response<StoreBusinessStatusDto> response) {
                consumer.accept(response.body());
            }

            @Override
            public void onFailure(Call<StoreBusinessStatusDto> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
