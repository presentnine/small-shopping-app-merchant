package com.sososhopping.merchant.store.manage.service;

import com.sososhopping.merchant.store.manage.dto.StoreBusinessStatusDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface StoreManagementService {

    @GET("store/{storeId}/businessstatus")
    Call<StoreBusinessStatusDto> requestBusinessStatus(@Header("token") String token, @Path("storeId") int storeId);

    @PATCH("store/{storeId}/businessstatus")
    Call<StoreBusinessStatusDto> requestBusinessStatusUpdate(@Header("token") String token, @Path("storeId") int storeId);
}
