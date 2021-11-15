package com.sososhopping.merchant.board.service;

import com.sososhopping.merchant.board.model.BoardListModel;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface BoardService {
    @GET("store/{storeId}/writing")
    Call<List<BoardListModel>> requestBoardList(@Header("token") String token, @Path("storeId") int storeId);

    @Multipart
    @POST("store/{storeId}/writing")
    Call<Void> requestBoardRegister(@Header("token") String token, @Path("storeId") int storeId, @Part MultipartBody.Part dto, @Part MultipartBody.Part image);
}
