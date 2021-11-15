package com.sososhopping.merchant.point.service;

import com.sososhopping.merchant.point.dto.PointRuleRequestDto;
import com.sososhopping.merchant.point.dto.PointRuleResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface PointService {
    @GET("store/{storeId}/pointpolicy")
    Call<PointRuleResponseDto> requestPointRule(@Header("token") String token, @Path("storeId") int storeId);

    @PATCH("store/{storeId}/pointpolicy")
    Call<Void> requestPointRuleUpdate(@Header("token") String token, @Path("storeId") int storeId, @Body PointRuleRequestDto dto);
}
