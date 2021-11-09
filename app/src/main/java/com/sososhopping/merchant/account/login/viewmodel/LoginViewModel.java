package com.sososhopping.merchant.account.login.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.account.login.dto.LoginRequestDto;
import com.sososhopping.merchant.account.login.dto.LoginResponseDto;
import com.sososhopping.merchant.account.login.repository.LoginRepository;

import java.util.function.BiConsumer;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> password = new MutableLiveData<>();

    private final LoginRepository loginRepository = LoginRepository.getInstance();

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public LoginRequestDto toLoginRequestDto() {
        return new LoginRequestDto(email.getValue(), password.getValue());
    }

    public void requestLogin(BiConsumer<LoginRequestDto, LoginResponseDto> onSuccess,
                             Runnable onFailed,
                             Runnable onError) {
        loginRepository.requestLogin(this.toLoginRequestDto(), onSuccess, onFailed, onError);
    }
}
