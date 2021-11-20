package com.sososhopping.merchant.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentStartBinding;

public class StartFragment extends Fragment {

    private FragmentStartBinding binding;

    public StartFragment() {

    }

    public static StartFragment newInstance() {
        return new StartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false);

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignupForm();
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLoginDialog();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void navigateToSignupForm() {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_startFragment_to_signupFormFragment);
    }

    private void navigateToLoginDialog() {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_startFragment_to_loginDialog);
    }
}
