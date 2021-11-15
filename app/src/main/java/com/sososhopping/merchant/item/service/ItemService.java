package com.sososhopping.merchant.item.service;

import com.sososhopping.merchant.item.model.ItemListModel;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ItemService {
    @GET("store/{storeId}/item")
    Call<List<ItemListModel>> requestStoreItemList(@Header("token") String token, @Path("storeId") int storeId);

    @Multipart
    @POST("store/{storeId}/item")
    Call<Void> requestRegisterItem(@Header("token") String token, @Path("storeId") int storeId, @Part MultipartBody.Part dto, @Part MultipartBody.Part image);
}
