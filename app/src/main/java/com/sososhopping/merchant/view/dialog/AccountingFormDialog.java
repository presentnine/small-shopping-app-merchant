package com.sososhopping.merchant.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.DialogAccountingFormBinding;
import com.sososhopping.merchant.viewmodel.AccountingRegisterViewModel;
import com.sososhopping.merchant.viewmodel.LoginViewModel;

public class AccountingFormDialog extends DialogFragment {

    private static final String STOREID = "storeId";

    private int storeId;

    DialogAccountingFormBinding binding;

    public AccountingFormDialog() {

    }

    public AccountingFormDialog newInstance() {
        AccountingFormDialog dialog = new AccountingFormDialog();
        Bundle args = new Bundle();
        args.putInt(STOREID, storeId);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            storeId = getArguments().getInt(STOREID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_accounting_form, container, false);

        AccountingRegisterViewModel viewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.NewInstanceFactory()).get(AccountingRegisterViewModel.class);
        binding.setAccountingRegisterViewModel(viewModel);

        Runnable onSuccess = this::closeDialog;
        Runnable onError = this::onNetworkError;

        binding.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.requestRegister(((MainActivity)getActivity()).getLoginToken(), storeId, onSuccess, onError);
            }
        });

        binding.type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.expenditure) viewModel.setIsIncome(false);
                else viewModel.setIsIncome(true);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getParentFragmentManager().setFragmentResult("key", null);
    }

    private void closeDialog() {
        this.dismiss();
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }
}
