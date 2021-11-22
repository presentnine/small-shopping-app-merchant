package com.sososhopping.merchant.model.review.Entity;

import com.google.gson.annotations.SerializedName;

public class ReviewList {

    @SerializedName("userId")
    int userId;
    @SerializedName("userName")
    String username;
    @SerializedName("score")
    double score;
    @SerializedName("content")
    String content;
    @SerializedName("createdAt")
    String createdAt;

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public double getScore() {
        return score;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
