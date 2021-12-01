package com.sososhopping.merchant.model.coupon.dto.response;

import com.google.gson.annotations.SerializedName;
import com.sososhopping.merchant.model.coupon.entity.CouponList;

import java.util.List;

public class CouponListResponseDto {

    @SerializedName("excepted")
    List<CouponList> expected;
    @SerializedName("being")
    List<CouponList> being;

    public List<CouponList> getExpected() {
        return expected;
    }

    public List<CouponList> getBeing() {
        return being;
    }
}
