package com.sososhopping.merchant.viewmodel;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import com.sososhopping.merchant.model.board.dto.request.BoardRegisterRequestDto;

public class BoardRegisterViewModel {
    MutableLiveData<Bitmap> bitmap = new MutableLiveData<>();

    MutableLiveData<String> category = new MutableLiveData<>();
    MutableLiveData<String> title = new MutableLiveData<>();
    MutableLiveData<String> description = new MutableLiveData<>();

    public MutableLiveData<Bitmap> getBitmap() {
        return bitmap;
    }

    public MutableLiveData<String> getCategory() {
        return category;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public void setCategory(String category) {
        this.category.setValue(category);
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap.setValue(bitmap);
    }

    private BoardRegisterRequestDto toDto() {
        return new BoardRegisterRequestDto(title.getValue(), description.getValue(), category.getValue());
    }
}
