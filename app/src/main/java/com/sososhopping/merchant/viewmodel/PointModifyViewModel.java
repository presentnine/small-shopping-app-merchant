package com.sososhopping.merchant.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.model.point.dto.request.PointModifyRequestDto;
import com.sososhopping.merchant.model.point.dto.response.PointModifyResponseDto;
import com.sososhopping.merchant.model.point.repository.PointRepository;

import java.util.function.Consumer;

public class PointModifyViewModel extends ViewModel {

    private final MutableLiveData<String> userPhone = new MutableLiveData<>();
    private final MutableLiveData<String> amount = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isSave = new MutableLiveData<>(true);

    public MutableLiveData<String> getUserPhone() {
        return userPhone;
    }

    public MutableLiveData<String> getAmount() {
        return amount;
    }

    public MutableLiveData<Boolean> getIsSave() {
        return isSave;
    }

    public void setIsSave(boolean isSave) {
        this.isSave.setValue(isSave);
    }

    public void requestUserCheck(String token, int storeId, Consumer<PointModifyResponseDto> onSuccess, Runnable onFailed, Runnable onError) {
        PointRepository.getInstance().requestPointCheck(token, storeId, userPhone.getValue(), onSuccess, onFailed, onError);
    }

    public void requestPointModify(String token, int storeId, Runnable onSuccess, Runnable onError) {
        PointRepository.getInstance().requestPointModify(token, storeId, this.toDto(), onSuccess, onError);
    }

    private PointModifyRequestDto toDto() {
        return new PointModifyRequestDto(userPhone.getValue(), Integer.parseInt(amount.getValue()), isSave.getValue());
    }
}
