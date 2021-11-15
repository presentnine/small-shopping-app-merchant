package com.sososhopping.merchant.point.view;

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

import com.sososhopping.merchant.R;
import com.sososhopping.merchant.account.login.viewmodel.LoginViewModel;
import com.sososhopping.merchant.databinding.DialogPointBasicFormBinding;
import com.sososhopping.merchant.point.viewmodel.PointInfoViewModel;

public class PointInfoFormDialog extends DialogFragment {

    DialogPointBasicFormBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_point_basic_form, container, false);

        PointInfoViewModel viewModel = new ViewModelProvider(requireActivity()).get(PointInfoViewModel.class);
        binding.setPointInfoViewModel(viewModel);

        binding.loginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
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
}
