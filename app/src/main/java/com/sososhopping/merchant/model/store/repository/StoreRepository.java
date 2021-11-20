package com.sososhopping.merchant.model.store.repository;

import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;
import com.sososhopping.merchant.model.store.service.StoreService;

public class StoreRepository {
    private static StoreRepository instance;
    private final StoreService service;

    private StoreRepository() {
        service = ApiServiceFactory.createService(StoreService.class);
    }

    public static synchronized StoreRepository getInstance() {
        if(instance == null) {
            instance = new StoreRepository();
        }

        return instance;
    }
}
