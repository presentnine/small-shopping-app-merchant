package com.sososhopping.merchant.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentPointManageBinding;
import com.sososhopping.merchant.model.point.dto.response.PointRuleResponseDto;
import com.sososhopping.merchant.model.point.repository.PointRepository;
import com.sososhopping.merchant.viewmodel.PointRuleViewModel;

import java.util.function.Consumer;

public class PointManageFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    FragmentPointManageBinding binding;

    public PointManageFragment() {

    }

    public static PointManageFragment newInstance(int storeId) {
        PointManageFragment fragment = new PointManageFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_point_manage, container, false);
        PointRuleViewModel viewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.NewInstanceFactory()).get(PointRuleViewModel.class);
        binding.setPointManageViewModel(viewModel);

        Consumer<PointRuleResponseDto> onPointRuleChecked = this::onPointRuleChecked;
        Runnable onError = this::onNetworkError;
        PointRepository.getInstance().requestPointRule(((MainActivity)getActivity()).getLoginToken(), storeId, onPointRuleChecked, onError);

        binding.pointRuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.requestUpdate(((MainActivity)getActivity()).getLoginToken(), storeId, onError);
            }
        });

        binding.pointHandleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                Navigation.findNavController(v).navigate(R.id.action_pointManageFragment_to_pointFormDialog, bundle);
            }
        });

        return binding.getRoot();
    }

    private void onPointRuleChecked(PointRuleResponseDto dto) {
        binding.enablePoint.setChecked(dto.isPointPolicyStatus());
        binding.signupFormEmailLayout.getEditText().setText(Double.toString(dto.getSaveRate()));
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }
}
