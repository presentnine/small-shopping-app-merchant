package com.sososhopping.merchant.model.coupon.dto.response;

import com.sososhopping.merchant.model.coupon.entity.CouponList;

import java.util.List;

public class CouponListResponseDto {

    List<CouponList> expected;
    List<CouponList> being;

    public List<CouponList> getExpected() {
        return expected;
    }

    public List<CouponList> getBeing() {
        return being;
    }
}
