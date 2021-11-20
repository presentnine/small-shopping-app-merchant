package com.sososhopping.merchant.model.board.repository;

import com.sososhopping.merchant.model.board.service.BoardService;
import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;

public class BoardRepository {

    private static BoardRepository instance;
    private final BoardService service;

    private BoardRepository() {
        this.service = ApiServiceFactory.createService(BoardService.class);
    }

    public static synchronized BoardRepository getInstance() {
        if(instance == null) {
            instance = new BoardRepository();
        }

        return instance;
    }
}
