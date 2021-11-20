package com.sososhopping.merchant.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.model.auth.dto.request.EmailDuplicationCheckRequestDto;
import com.sososhopping.merchant.model.auth.dto.request.SignupRequestDto;

public class SignupViewModel extends ViewModel {

    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> password = new MutableLiveData<>();
    private final MutableLiveData<String> passwordCheck = new MutableLiveData<>();
    private final MutableLiveData<String> name = new MutableLiveData<>();
    private final MutableLiveData<String> phone = new MutableLiveData<>();

    private final MutableLiveData<Boolean> emailDupChecked = new MutableLiveData<>(false);

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<String> getPasswordCheck() {
        return passwordCheck;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getPhone() {
        return phone;
    }

    public EmailDuplicationCheckRequestDto toEmailDuplicationCheckRequestDto() {
        return new EmailDuplicationCheckRequestDto(this.email.getValue());
    }

    public SignupRequestDto toSignupRequestDto() {
        return new SignupRequestDto(this.email.getValue(),
                this.password.getValue(),
                this.name.getValue(),
                this.phone.getValue());
    }
}
