package com.sososhopping.merchant.main.model;

import com.google.gson.annotations.SerializedName;

public class ShopListModel {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("imageUrl")
    String imageUrl;
    @SerializedName("description")
    String description;
    @SerializedName("storeStatus")
    String status;

    public ShopListModel(int id, String imageUrl, String name, String description, String status) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
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
