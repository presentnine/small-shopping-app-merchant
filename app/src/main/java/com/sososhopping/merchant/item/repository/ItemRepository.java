package com.sososhopping.merchant.item.repository;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.GsonBuilder;
import com.sososhopping.merchant.common.retrofit.ApiServiceFactory;
import com.sososhopping.merchant.common.retrofit.BitmapRequestBody;
import com.sososhopping.merchant.item.dto.ItemRegisterRequestDto;
import com.sososhopping.merchant.item.model.ItemListModel;
import com.sososhopping.merchant.item.service.ItemService;
import com.sososhopping.merchant.main.model.ShopListModel;
import com.sososhopping.merchant.main.repository.ShopListRepository;
import com.sososhopping.merchant.main.service.ShopListService;

import java.util.List;
import java.util.function.Consumer;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {
    private static ItemRepository instance;
    private final ItemService shopListService;

    private ItemRepository() {
        this.shopListService = ApiServiceFactory.createService(ItemService.class);
    }

    public static synchronized ItemRepository getInstance() {
        if(instance == null) {
            instance = new ItemRepository();
        }

        return instance;
    }

    public void requestMyShopList(String token, int storeId, Consumer<List<ItemListModel>> onSuccess, Runnable onFailed) {
        shopListService.requestStoreItemList(token, storeId).enqueue(new Callback<List<ItemListModel>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<ItemListModel>> call, Response<List<ItemListModel>> response) {
                if (response.code() == 200) {
                    onSuccess.accept(response.body());
                } else {
                    System.out.println(response.code());
                    onFailed.run();
                }
            }

            @Override
            public void onFailure(Call<List<ItemListModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void requestRegisterItem(String token, int storeId, ItemRegisterRequestDto dto, Bitmap bitmap, Runnable onSuccess){
        shopListService.requestRegisterItem(token, storeId, MultipartBody.Part.createFormData("dto", "dto", RequestBody.create(MediaType.parse("application/json"), new GsonBuilder().create().toJson(dto))), MultipartBody.Part.createFormData("img", "image.jpg", new BitmapRequestBody(bitmap))).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                System.out.println("success");
                onSuccess.run();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
