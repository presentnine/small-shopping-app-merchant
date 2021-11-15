package com.sososhopping.merchant.item.viewmodel;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.item.dto.ItemRegisterRequestDto;
import com.sososhopping.merchant.item.repository.ItemRepository;

public class ItemRegisterViewModel extends ViewModel {
    MutableLiveData<Bitmap> bitmap = new MutableLiveData<>();

    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<String> description = new MutableLiveData<>();
    MutableLiveData<String> unit = new MutableLiveData<>();
    MutableLiveData<String> unitPrice = new MutableLiveData<>();

    MutableLiveData<Boolean> salesStatus = new MutableLiveData<>(false);

    public MutableLiveData<Bitmap> getBitmap() {
        return bitmap;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public MutableLiveData<String> getUnit() {
        return unit;
    }

    public MutableLiveData<String> getUnitPrice() {
        return unitPrice;
    }

    public MutableLiveData<Boolean> getSalesStatus() {
        return salesStatus;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap.setValue(bitmap);
    }

    public void requestRegister(String token, int storeId, Runnable onSuccess) {
        System.out.println(this.name.getValue());
        ItemRepository.getInstance().requestRegisterItem(token, storeId, this.toDto(), this.bitmap.getValue(), onSuccess);
    }

    private ItemRegisterRequestDto toDto() {
        System.out.println(unitPrice.getValue());
        return new ItemRegisterRequestDto(name.getValue(), description.getValue(), unit.getValue(), Integer.valueOf(unitPrice.getValue()), salesStatus.getValue());
    }
}
