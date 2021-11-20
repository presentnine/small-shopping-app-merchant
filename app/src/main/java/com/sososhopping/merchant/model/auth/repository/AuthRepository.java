package com.sososhopping.merchant.model.auth.repository;

import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;
import com.sososhopping.merchant.model.auth.service.AuthService;

public class AuthRepository {

    private static AuthRepository instance;
    private final AuthService service;

    private AuthRepository() {
        service = ApiServiceFactory.createService(AuthService.class);
    }

    public static synchronized AuthRepository getInstance() {
        if(instance == null) {
            instance = new AuthRepository();
        }

        return instance;
    }
}
