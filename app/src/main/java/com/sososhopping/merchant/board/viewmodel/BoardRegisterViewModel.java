package com.sososhopping.merchant.board.viewmodel;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.board.dto.BoardRegisterDto;
import com.sososhopping.merchant.board.repository.BoardRepository;
import com.sososhopping.merchant.item.dto.ItemRegisterRequestDto;
import com.sososhopping.merchant.item.repository.ItemRepository;

public class BoardRegisterViewModel extends ViewModel {

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

    public void requestRegister(String token, int storeId, Runnable onSuccess) {
        BoardRepository.getInstance().requestBoardRegister(token, storeId, this.toDto(), this.bitmap.getValue(), onSuccess);
    }

    private BoardRegisterDto toDto() {
        return new BoardRegisterDto(title.getValue(), description.getValue(), category.getValue());
    }
}
