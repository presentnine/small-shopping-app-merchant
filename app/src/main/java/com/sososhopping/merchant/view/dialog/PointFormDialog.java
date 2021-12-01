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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.DialogPointFormBinding;
import com.sososhopping.merchant.model.point.dto.response.PointModifyResponseDto;
import com.sososhopping.merchant.viewmodel.PointModifyViewModel;

import java.util.function.Consumer;

public class PointFormDialog extends DialogFragment {

    private static final String STOREID = "storeId";

    private int storeId;

    DialogPointFormBinding binding;

    public PointFormDialog() {

    }

    public PointFormDialog newInstance() {
        PointFormDialog dialog = new PointFormDialog();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_point_form, container, false);

        NavController navController = NavHostFragment.findNavController(this);

        ViewModelProvider viewModelProvider = new ViewModelProvider(navController.getViewModelStoreOwner(R.id.navigationPoint));

        PointModifyViewModel viewModel = viewModelProvider.get(PointModifyViewModel.class);
        binding.setPointModifyViewModel(viewModel);

        Consumer<PointModifyResponseDto> onSuccess = this::onSuccess;
        Runnable onFailed = this::onUserNotFound;
        Runnable onError = this::onNetworkError;

        binding.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.requestUserCheck(((MainActivity)getActivity()).getLoginToken(), storeId, onSuccess, onFailed, onError);
            }
        });

        binding.type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.earn) viewModel.setIsSave(true);
                else viewModel.setIsSave(false);
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

    private void navigateToCheck() {
        Bundle bundle = new Bundle();
        bundle.putInt(STOREID, storeId);
        NavHostFragment.findNavController(this).navigate(R.id.action_pointFormDialog_to_pointCheckDialog, bundle);
    }

    private void onSuccess(PointModifyResponseDto dto) {
        Bundle bundle = new Bundle();
        bundle.putInt(STOREID, storeId);
        bundle.putString("userName", dto.getUserName());
        bundle.putInt("userPoint", dto.getAmount());
        NavHostFragment.findNavController(this).navigate(R.id.action_pointFormDialog_to_pointCheckDialog, bundle);
    }

    private void onUserNotFound() {
        binding.customerNumber.setErrorEnabled(true);
        binding.customerNumber.setError("일치하는 고객을 찾을 수 없습니다.");
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }
}
