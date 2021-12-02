package com.sososhopping.merchant.model.order.repository;

import com.sososhopping.merchant.model.order.service.OrderService;
import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;

public class OrderRepository {

    private static OrderRepository instance;
    private OrderService service;

    public OrderRepository() {
        this.service = ApiServiceFactory.createService(OrderService.class);
    }

    public static synchronized OrderRepository getInstance() {
        if(instance == null) {
            instance = new OrderRepository();
        }

        return instance;
    }
}
