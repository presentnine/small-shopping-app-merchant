package com.sososhopping.merchant.model.point.repository;

import com.sososhopping.merchant.model.point.service.PointService;
import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;

public class PointRepository {

    private static PointRepository instance;
    private final PointService service;

    private PointRepository() {
        this.service = ApiServiceFactory.createService(PointService.class);
    }

    public static synchronized PointRepository getInstance() {
        if(instance == null) {
            instance = new PointRepository();
        }

        return instance;
    }
}
