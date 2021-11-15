package com.sososhopping.merchant.item.model;

import com.google.gson.annotations.SerializedName;

public class ItemListModel {
    int id;
    int storeId;
    String imgUrl;
    String name;
    String description;
    String purchaseUnit;
    @SerializedName("price")
    int unitPrice;
    boolean saleStatus;

    public int getId() {
        return id;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPurchaseUnit() {
        return purchaseUnit;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public boolean isSaleStatus() {
        return saleStatus;
    }
}
