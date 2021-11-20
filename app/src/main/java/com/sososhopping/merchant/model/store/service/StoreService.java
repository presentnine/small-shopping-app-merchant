package com.sososhopping.merchant.model.store.service;

import com.sososhopping.merchant.model.store.dto.response.StoreOpenStatusResponseDto;
import com.sososhopping.merchant.model.store.entity.StoreList;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface StoreService {

    @GET("store")
    Call<List<StoreList>> requestStoreList(@Header("token") String token);

    @Multipart
    @POST("store")
    Call<Void> requestStoreRegister(@Header("token") String token, @Part MultipartBody.Part dto, @Part MultipartBody.Part image);

    @GET("store/{storeId}/businessstatus")
    Call<StoreOpenStatusResponseDto> requestStoreOpenStatus(@Header("token") String token, @Path("storeId") int StoreId);

    @PATCH("store/{storeId}/businessstatus")
    Call<StoreOpenStatusResponseDto> requestStoreOpenStatusChange(@Header("token") String token, @Path("storeId") int StoreId);
}
