package com.sososhopping.merchant.main.model;

import com.google.gson.annotations.SerializedName;

public class ShopListModel {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("imgUrl")
    String imageUrl;
    @SerializedName("description")
    String description;
    @SerializedName("storeStatus")
    String status;

    public ShopListModel(int id, String name, String imageUrl, String description, String status) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
