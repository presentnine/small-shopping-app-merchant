package com.sososhopping.merchant.model.coupon.service;

import com.sososhopping.merchant.model.coupon.dto.request.CouponRegisterRequestDto;
import com.sososhopping.merchant.model.coupon.dto.response.CouponListResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CouponService {

    @GET("store/{storeId}/coupon")
    Call<CouponListResponseDto> requestCouponList(@Header("token") String token, @Path("storeId") int storeId);

    @POST("store/{storeId}/coupon")
    Call<Void> requestCouponRegister(@Header("token") String token, @Path("storeId") int storeId, @Body CouponRegisterRequestDto dto);
}
