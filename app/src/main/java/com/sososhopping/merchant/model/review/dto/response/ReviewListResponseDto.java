package com.sososhopping.merchant.model.review.dto.response;

import com.google.gson.annotations.SerializedName;
import com.sososhopping.merchant.model.review.Entity.ReviewList;

import java.util.List;

public class ReviewListResponseDto {

    @SerializedName("averageScore")
    double averageScore;
    @SerializedName("size")
    int reviewCount;
    @SerializedName("reviews")
    List<ReviewList> reviewLists;

    public double getAverageScore() {
        return averageScore;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public List<ReviewList> getReviewLists() {
        return reviewLists;
    }
}
