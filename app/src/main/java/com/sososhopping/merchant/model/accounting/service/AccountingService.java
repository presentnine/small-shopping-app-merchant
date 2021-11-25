package com.sososhopping.merchant.model.accounting.service;

import com.sososhopping.merchant.model.accounting.dto.request.AccountingRegisterRequestDto;
import com.sososhopping.merchant.model.accounting.entity.AccountingList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccountingService {

    @GET("store/{storeId}/accounting")
    Call<List<AccountingList>> requestAccountingList(@Header("token") String token, @Path("storeId") int storeId, @Query("yearMonth") String yearMonth);

    @POST("store/{storeId}/accounting")
    Call<Void> requestAccountingRegister(@Header("token") String token, @Path("storeId") int storeId, @Body AccountingRegisterRequestDto dto);
}
