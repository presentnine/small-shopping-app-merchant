package com.sososhopping.merchant.main.service;

import com.sososhopping.merchant.main.model.ShopListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ShopListService {
    @GET("store")
    Call<List<ShopListModel>> requestMyShopList(@Header("token") String token);
}
