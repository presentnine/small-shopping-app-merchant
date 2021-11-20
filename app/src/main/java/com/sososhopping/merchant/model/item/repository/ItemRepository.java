package com.sososhopping.merchant.model.item.repository;

import com.sososhopping.merchant.model.item.service.ItemService;
import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;

public class ItemRepository {

    private static ItemRepository instance;
    private final ItemService service;

    private ItemRepository() {
        this.service = ApiServiceFactory.createService(ItemService.class);
    }

    public static synchronized ItemRepository getInstance() {
        if(instance == null) {
            instance = new ItemRepository();
        }

        return instance;
    }
}
