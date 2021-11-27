package com.sososhopping.merchant.model.coupon.repository;

import com.sososhopping.merchant.model.coupon.service.CouponService;
import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;

public class CouponRepository {

    private static CouponRepository instance;
    private final CouponService service;

    private CouponRepository() {
        this.service = ApiServiceFactory.createService(CouponService.class);
    }

    public synchronized static CouponRepository getInstance() {
        if (instance == null) {
            instance = new CouponRepository();
        }
        return instance;
    }
}
