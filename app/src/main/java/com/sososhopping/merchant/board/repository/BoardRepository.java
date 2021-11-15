package com.sososhopping.merchant.board.repository;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.GsonBuilder;
import com.sososhopping.merchant.board.dto.BoardRegisterDto;
import com.sososhopping.merchant.board.model.BoardListModel;
import com.sososhopping.merchant.board.service.BoardService;
import com.sososhopping.merchant.common.retrofit.ApiServiceFactory;
import com.sososhopping.merchant.common.retrofit.BitmapRequestBody;
import com.sososhopping.merchant.item.dto.ItemRegisterRequestDto;
import com.sososhopping.merchant.item.model.ItemListModel;
import com.sososhopping.merchant.item.repository.ItemRepository;

import java.util.List;
import java.util.function.Consumer;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardRepository {

    private static BoardRepository instance;
    private final BoardService shopListService;

    private BoardRepository() {
        this.shopListService = ApiServiceFactory.createService(BoardService.class);
    }

    public static synchronized BoardRepository getInstance() {
        if(instance == null) {
            instance = new BoardRepository();
        }

        return instance;
    }

    public void requestBoardList(String token, int storeId, Consumer<List<BoardListModel>> consumer, Runnable onFailed) {
        shopListService.requestBoardList(token, storeId).enqueue(new Callback<List<BoardListModel>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<BoardListModel>> call, Response<List<BoardListModel>> response) {
                if (response.code() == 200) {
                    consumer.accept(response.body());
                } else {
                    System.out.println(response.code());
                    System.out.println(response.body());
                    onFailed.run();
                }
            }

            @Override
            public void onFailure(Call<List<BoardListModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void requestBoardRegister(String token, int storeId, BoardRegisterDto dto, Bitmap bitmap){
        shopListService.requestBoardRegister(token, storeId, MultipartBody.Part.createFormData("dto", "dto", RequestBody.create(MediaType.parse("application/json"), new GsonBuilder().create().toJson(dto))), MultipartBody.Part.createFormData("img", "image.jpg", new BitmapRequestBody(bitmap))).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                System.out.println("success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
