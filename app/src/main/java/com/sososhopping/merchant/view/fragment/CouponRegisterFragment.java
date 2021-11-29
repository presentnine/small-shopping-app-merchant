package com.sososhopping.merchant.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentCouponRegisterBinding;
import com.sososhopping.merchant.viewmodel.BoardRegisterViewModel;
import com.sososhopping.merchant.viewmodel.CouponRegisterViewModel;

public class CouponRegisterFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    FragmentCouponRegisterBinding binding;

    public CouponRegisterFragment() {

    }

    public static CouponRegisterFragment newInstance(int storeId) {
        CouponRegisterFragment fragment = new CouponRegisterFragment();
        Bundle args = new Bundle();
        args.putInt(STOREID, storeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            storeId = getArguments().getInt(STOREID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coupon_register, container, false);

        CouponRegisterViewModel viewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.NewInstanceFactory()).get(CouponRegisterViewModel.class);
        binding.setCouponReigsterViewModel(viewModel);


        binding.typeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rate) {
                    viewModel.setCouponType("RATE");
                    binding.type.setText("%");
                    binding.couponAmountLayout.setHint("할인 비율");
                } else {
                    viewModel.setCouponType("FIX");
                    binding.type.setText("원");
                    binding.couponAmountLayout.setHint("할인 금액");
                }
            }
        });

        return binding.getRoot();
    }
}