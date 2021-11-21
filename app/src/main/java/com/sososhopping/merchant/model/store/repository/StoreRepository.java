package com.sososhopping.merchant.model.store.repository;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sososhopping.merchant.model.store.dto.request.StoreRegisterRequestDto;
import com.sososhopping.merchant.model.store.dto.response.StoreOpenStatusResponseDto;
import com.sososhopping.merchant.model.store.entity.StoreList;
import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;
import com.sososhopping.merchant.model.store.service.StoreService;
import com.sososhopping.merchant.util.retrofit.request.BitmapRequestBody;
import com.sososhopping.merchant.util.retrofit.request.DtoJsonRequestBody;

import java.util.List;
import java.util.function.Consumer;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreRepository {
    private static StoreRepository instance;
    private final StoreService service;

    private StoreRepository() {
        service = ApiServiceFactory.createService(StoreService.class);
    }

    public static synchronized StoreRepository getInstance() {
        if(instance == null) {
            instance = new StoreRepository();
        }

        return instance;
    }

    public void requestRegister(String token, Bitmap image, StoreRegisterRequestDto dto, Runnable onSuccess, Runnable onError) {
        service.requestStoreRegister(token, MultipartBody.Part.createFormData("dto", "dto", new DtoJsonRequestBody<>(dto)), MultipartBody.Part.createFormData("img", "image.jpg", new BitmapRequestBody(image))).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                if (response.code() == 201) {
                    onSuccess.run();
                } else {
                    onError.run();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onError.run();
            }
        });
    }

    public void requestStoreList(String token, Consumer<List<StoreList>> onSuccess, Runnable onError) {
        service.requestStoreList(token).enqueue(new Callback<List<StoreList>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<StoreList>> call, Response<List<StoreList>> response) {
                if (response.code() == 200) {
                    onSuccess.accept(response.body());
                } else {
                    onError.run();
                }
            }

            @Override
            public void onFailure(Call<List<StoreList>> call, Throwable t) {
                onError.run();
            }
        });
    }

    public void requestStoreBusinessStatus(String token, int storeId, Consumer<StoreOpenStatusResponseDto> consumer, Runnable onError) {
        service.requestStoreOpenStatus(token, storeId).enqueue(new Callback<StoreOpenStatusResponseDto>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<StoreOpenStatusResponseDto> call, Response<StoreOpenStatusResponseDto> response) {
                System.out.println(response.code());
                System.out.println(response.body());
                consumer.accept(response.body());
            }

            @Override
            public void onFailure(Call<StoreOpenStatusResponseDto> call, Throwable t) {
                onError.run();
            }
        });
    }

    public void requestStoreBusinessStatusUpdate(String token, int storeId, Consumer<StoreOpenStatusResponseDto> consumer, Runnable onError) {
        service.requestStoreOpenStatusChange(token, storeId).enqueue(new Callback<StoreOpenStatusResponseDto>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<StoreOpenStatusResponseDto> call, Response<StoreOpenStatusResponseDto> response) {
                consumer.accept(response.body());
            }

            @Override
            public void onFailure(Call<StoreOpenStatusResponseDto> call, Throwable t) {
                onError.run();
            }
        });
    }
}
