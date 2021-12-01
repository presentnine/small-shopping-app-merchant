package com.sososhopping.merchant.model.coupon.dto.request;

import com.google.gson.annotations.SerializedName;

public class CouponModifyRequestDto {

    String userPhone;
    String couponCode;

    public CouponModifyRequestDto(String userPhone, String couponCode) {
        this.userPhone = userPhone;
        this.couponCode = couponCode;
    }
}
