package com.sososhopping.merchant.store.register.repository;

import android.graphics.Bitmap;

import com.google.gson.GsonBuilder;
import com.sososhopping.merchant.common.retrofit.ApiServiceFactory;
import com.sososhopping.merchant.common.retrofit.BitmapRequestBody;
import com.sososhopping.merchant.store.register.dto.StoreRegisterRequestDto;
import com.sososhopping.merchant.store.register.service.StoreRegisterService;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreRegisterRepository {
    private static StoreRegisterRepository instance;
    private final StoreRegisterService storeRegisterService;

    private StoreRegisterRepository() {
        this.storeRegisterService = ApiServiceFactory.createService(StoreRegisterService.class);
    }

    public static synchronized StoreRegisterRepository getInstance() {
        if(instance == null) {
            instance = new StoreRegisterRepository();
        }

        return instance;
    }

    public void requestRegister(String token, Bitmap image, StoreRegisterRequestDto dto) {
        storeRegisterService.requestRegisterStore(token, MultipartBody.Part.createFormData("dto", "dto", RequestBody.create(MediaType.parse("application/json"), new GsonBuilder().create().toJson(dto))), MultipartBody.Part.createFormData("img", "image.jpg", new BitmapRequestBody(image))).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
