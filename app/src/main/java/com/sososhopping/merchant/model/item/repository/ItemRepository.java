package com.sososhopping.merchant.model.item.repository;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sososhopping.merchant.model.item.dto.request.ItemRegisterRequestDto;
import com.sososhopping.merchant.model.item.entity.ItemList;
import com.sososhopping.merchant.model.item.service.ItemService;
import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;
import com.sososhopping.merchant.util.retrofit.request.BitmapRequestBody;
import com.sososhopping.merchant.util.retrofit.request.DtoJsonRequestBody;

import java.util.List;
import java.util.function.Consumer;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {

    private static ItemRepository instance;
    private final ItemService service;

    private ItemRepository() {
        this.service = ApiServiceFactory.createService(ItemService.class);
    }

    public static synchronized ItemRepository getInstance() {
        if(instance == null) {
            instance = new ItemRepository();
        }

        return instance;
    }

    public void requestItemList(String token, int storeId, Consumer<List<ItemList>> onSuccess, Runnable onError) {
        service.requestStoreItemList(token, storeId).enqueue(new Callback<List<ItemList>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<ItemList>> call, Response<List<ItemList>> response) {
                if (response.code() == 200) {
                    onSuccess.accept(response.body());
                } else {
                    onError.run();
                }
            }

            @Override
            public void onFailure(Call<List<ItemList>> call, Throwable t) {
                onError.run();
            }
        });
    }

    public void requestRegisterItem(String token, int storeId, ItemRegisterRequestDto dto, Bitmap bitmap, Runnable onSuccess, Runnable onError){
        service.requestRegisterItem(token, storeId, MultipartBody.Part.createFormData("dto", "dto", new DtoJsonRequestBody<>(dto)), MultipartBody.Part.createFormData("img", "image.jpg", new BitmapRequestBody(bitmap))).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
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
}
