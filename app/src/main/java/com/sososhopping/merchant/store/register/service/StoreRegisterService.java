package com.sososhopping.merchant.store.register.service;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface StoreRegisterService {

    @Multipart
    @POST("store")
    Call<Void> requestRegisterStore(@Header("token") String token, @Part MultipartBody.Part dto, @Part MultipartBody.Part image);
}
