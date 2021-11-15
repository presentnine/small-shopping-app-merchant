package com.sososhopping.merchant.board.model;

public class BoardListModel {
    int id;
    int storeId;
    String title;
    String content;
    String writingType;
    String imgUrl;
    String createdAt;

    public int getId() {
        return id;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWritingType() {
        return writingType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
