package com.sososhopping.merchant.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.model.auth.dto.request.LoginRequestDto;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> password = new MutableLiveData<>();

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public LoginRequestDto toLoginRequestDto() {
        return new LoginRequestDto(email.getValue(), password.getValue());
    }
}
