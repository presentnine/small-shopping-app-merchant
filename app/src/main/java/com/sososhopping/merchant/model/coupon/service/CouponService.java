package com.sososhopping.merchant.model.coupon.service;

import com.sososhopping.merchant.model.coupon.dto.request.CouponModifyRequestDto;
import com.sososhopping.merchant.model.coupon.dto.request.CouponRegisterRequestDto;
import com.sososhopping.merchant.model.coupon.dto.response.CouponListResponseDto;
import com.sososhopping.merchant.model.coupon.dto.response.CouponModifyResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CouponService {

    @GET("store/{storeId}/coupon")
    Call<CouponListResponseDto> requestCouponList(@Header("token") String token, @Path("storeId") int storeId);

    @POST("store/{storeId}/coupon")
    Call<Void> requestCouponRegister(@Header("token") String token, @Path("storeId") int storeId, @Body CouponRegisterRequestDto dto);

    @GET("store/{storeId}/coupon/local")
    Call<CouponModifyResponseDto> requestCouponCheck(@Header("token") String token, @Path("storeId") int storeId, @Query("userPhone") String userPhone, @Query("couponCode") String couponCode);

    @POST("store/{storeId}/coupon/local")
    Call<Void> requestCouponModify(@Header("token") String token, @Path("storeId") int storeId, @Body CouponModifyRequestDto dto);
}
