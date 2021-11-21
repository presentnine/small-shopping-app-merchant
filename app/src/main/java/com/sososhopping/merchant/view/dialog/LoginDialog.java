package com.sososhopping.merchant.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.DialogLoginBinding;
import com.sososhopping.merchant.model.auth.dto.request.LoginRequestDto;
import com.sososhopping.merchant.model.auth.dto.response.LoginResponseDto;
import com.sososhopping.merchant.util.Constant;
import com.sososhopping.merchant.util.sharedpreferences.SharedPreferenceManager;
import com.sososhopping.merchant.viewmodel.LoginViewModel;

import java.util.function.BiConsumer;

public class LoginDialog extends DialogFragment {

    DialogLoginBinding binding;

    public LoginDialog() {

    }

    public static LoginDialog newInstance() {
        return new LoginDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_login, container, true);

        LoginViewModel viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        binding.setLoginViewModel(viewModel);

        BiConsumer<LoginRequestDto, LoginResponseDto> onSuccess = this::onLoggedIn;
        Runnable onFailed = this::onLoginFailed;
        Runnable onError = this::onNetworkError;

        binding.loginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.requestLogin(
                        onSuccess,
                        onFailed,
                        onError
                );
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private void onLoginFailed() {
        TextInputLayout emailLayout = binding.loginEmailLayout;
        TextInputLayout passwordLayout = binding.loginPasswordLayout;

        emailLayout.setErrorEnabled(true);
        emailLayout.setError(getString(R.string.login_error_incorrect));

        passwordLayout.setErrorEnabled(true);
        passwordLayout.setError(getString(R.string.login_error_incorrect));
    }

    private void onLoggedIn(LoginRequestDto requestDto, LoginResponseDto responseDto) {
        String id = requestDto.getEmail();
        String password = requestDto.getPassword();
        String token = responseDto.getToken();
        SharedPreferenceManager.setString(getContext(), Constant.SHARED_PREFERENCE_KEY_ID, id);
        SharedPreferenceManager.setString(getContext(), Constant.SHARED_PREFERENCE_KEY_PASSWORD, password);
        ((MainActivity) getActivity()).setLoginToken(token);
        navigateToMain();
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }

    private void navigateToMain() {
        NavHostFragment.findNavController(this).navigate(R.id.action_loginDialog_to_mainFragment);
    }
}
