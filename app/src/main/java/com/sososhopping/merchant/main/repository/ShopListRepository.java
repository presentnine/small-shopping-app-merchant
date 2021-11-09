package com.sososhopping.merchant.main.repository;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sososhopping.merchant.common.retrofit.ApiServiceFactory;
import com.sososhopping.merchant.main.model.ShopListModel;
import com.sososhopping.merchant.main.service.ShopListService;

import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopListRepository {
    private static ShopListRepository instance;
    private final ShopListService shopListService;

    private ShopListRepository() {
        this.shopListService = ApiServiceFactory.createService(ShopListService.class);
    }

    public static synchronized ShopListRepository getInstance() {
        if(instance == null) {
            instance = new ShopListRepository();
        }

        return instance;
    }

    public void requestMyShopList(String token, Consumer<List<ShopListModel>> onSuccess, Runnable onFailed) {
        shopListService.requestMyShopList(token).enqueue(new Callback<List<ShopListModel>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<ShopListModel>> call, Response<List<ShopListModel>> response) {
                if (response.code() == 200) {
                    onSuccess.accept(response.body());
                } else {
                    System.out.println(response.code());
                    System.out.println(response.body());
                    onFailed.run();
                }
            }

            @Override
            public void onFailure(Call<List<ShopListModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
