package com.sososhopping.merchant.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.model.auth.dto.request.EmailDuplicationCheckRequestDto;
import com.sososhopping.merchant.model.auth.dto.request.SignupRequestDto;
import com.sososhopping.merchant.model.auth.repository.AuthRepository;

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

    public void requestEmailDupCheck(Runnable onInappropriate,
                                     Runnable onChecked,
                                     Runnable onDuplicated,
                                     Runnable onError) {
        Runnable onNotDuplicated = () -> {
            onChecked.run();
            emailDupChecked.setValue(true);
        };
        if (email.getValue() == null || email.getValue().isEmpty() || !email.getValue().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) onInappropriate.run();
        else {
            AuthRepository.getInstance().requestEmailDuplicationCheck(this.toEmailDuplicationCheckRequestDto(), onNotDuplicated, onDuplicated, onError);
        }
    }

    public boolean validateForm(Runnable onPasswordProper,
                                Runnable onPasswordMatch,
                                Runnable onNameProper,
                                Runnable onPhoneProper,
                                Runnable onEmailNotChecked,
                                Runnable onPasswordTooShort,
                                Runnable onPasswordCheckEmpty,
                                Runnable onPasswordNotMatched,
                                Runnable onNameEmpty,
                                Runnable onPhoneEmpty) {
        boolean isValid = true;

        if (!emailDupChecked.getValue()) {
            onEmailNotChecked.run();
            isValid = false;
        }

        if (password.getValue() == null || password.getValue().length() < 8) {
            onPasswordTooShort.run();
            isValid = false;
        } else {
            onPasswordProper.run();
        }

        if (passwordCheck.getValue() == null || passwordCheck.getValue().isEmpty()){
            onPasswordCheckEmpty.run();
            isValid = false;
        }
        else if (!passwordCheck.getValue().equals(password.getValue())) {
            onPasswordNotMatched.run();
            isValid = false;
        } else {
            onPasswordMatch.run();
        }

        if (name.getValue() == null || name.getValue().isEmpty()) {
            onNameEmpty.run();
        } else {
            onNameProper.run();
        }

        if (phone.getValue() == null || phone.getValue().isEmpty()) {
            onPhoneEmpty.run();
        } else {
            onPhoneProper.run();
        }

        return isValid;
    }

    public void requestSignup(Runnable onSuccess,
                              Runnable onError) {
        AuthRepository.getInstance().requestSignup(this.toSignupRequestDto(), onSuccess, onError);
    }

    private EmailDuplicationCheckRequestDto toEmailDuplicationCheckRequestDto() {
        return new EmailDuplicationCheckRequestDto(this.email.getValue());
    }

    private SignupRequestDto toSignupRequestDto() {
        return new SignupRequestDto(this.email.getValue(),
                this.password.getValue(),
                this.name.getValue(),
                this.phone.getValue());
    }
}
