package com.sososhopping.merchant.model.board.repository;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sososhopping.merchant.model.board.dto.request.BoardRegisterRequestDto;
import com.sososhopping.merchant.model.board.entity.BoardList;
import com.sososhopping.merchant.model.board.service.BoardService;
import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;
import com.sososhopping.merchant.util.retrofit.request.BitmapRequestBody;
import com.sososhopping.merchant.util.retrofit.request.DtoJsonRequestBody;

import java.util.List;
import java.util.function.Consumer;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardRepository {

    private static BoardRepository instance;
    private final BoardService service;

    private BoardRepository() {
        this.service = ApiServiceFactory.createService(BoardService.class);
    }

    public static synchronized BoardRepository getInstance() {
        if(instance == null) {
            instance = new BoardRepository();
        }

        return instance;
    }

    public void requestBoardList(String token, int storeId, Consumer<List<BoardList>> consumer, Runnable onError) {
        service.requestBoardList(token, storeId).enqueue(new Callback<List<BoardList>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<BoardList>> call, Response<List<BoardList>> response) {
                if (response.code() == 200) {
                    consumer.accept(response.body());
                } else {
                    onError.run();
                }
            }

            @Override
            public void onFailure(Call<List<BoardList>> call, Throwable t) {
                onError.run();
            }
        });
    }

    public void requestBoardRegister(String token,
                                     int storeId,
                                     BoardRegisterRequestDto dto,
                                     Bitmap bitmap,
                                     Runnable onSuccess,
                                     Runnable onError){
        service.requestBoardRegister(token, storeId, MultipartBody.Part.createFormData("dto", "dto", new DtoJsonRequestBody<>(dto)), MultipartBody.Part.createFormData("img", "image.jpg", new BitmapRequestBody(bitmap))).enqueue(new Callback<Void>() {
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
