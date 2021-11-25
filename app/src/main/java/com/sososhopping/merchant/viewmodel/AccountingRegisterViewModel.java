package com.sososhopping.merchant.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.model.accounting.dto.request.AccountingRegisterRequestDto;
import com.sososhopping.merchant.model.accounting.repository.AccountingRepository;

public class AccountingRegisterViewModel extends ViewModel {

    private final MutableLiveData<String> description = new MutableLiveData<>();
    private final MutableLiveData<String> dateTime = new MutableLiveData<>();
    private final MutableLiveData<String> amount = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isSave = new MutableLiveData<>(true);

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public MutableLiveData<String> getDateTime() {
        return dateTime;
    }

    public MutableLiveData<String> getAmount() {
        return amount;
    }

    public void requestRegister(String token, int storeId, Runnable onSuccess, Runnable onError) {
        AccountingRepository.getInstance().requestAccountingRegister(token, storeId, toDto(), onSuccess, onError);
    }

    private AccountingRegisterRequestDto toDto() {
        return new AccountingRegisterRequestDto(dateTime.getValue(),
                isSave.getValue() ? Integer.parseInt(amount.getValue()) : Integer.parseInt(amount.getValue()) * -1,
                description.getValue());
    }
}
