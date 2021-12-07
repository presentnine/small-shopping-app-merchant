package com.sososhopping.merchant.view.fragment;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentNestedStoreConsoleBinding;
import com.sososhopping.merchant.model.store.dto.response.StoreOpenStatusResponseDto;
import com.sososhopping.merchant.model.store.repository.StoreRepository;
import com.sososhopping.merchant.util.token.TokenStore;

import java.util.function.Consumer;

public class NestedStoreConsoleFragment extends Fragment {

    private static final String STOREID = "storeId";
    private static final String STORENAME = "storeName";

    private int storeId;
    private String storeName;

    FragmentNestedStoreConsoleBinding binding;

    Resources resources;

    public NestedStoreConsoleFragment() {

    }

    public static NestedStoreConsoleFragment newInstance(int storeId, String storeName) {
        NestedStoreConsoleFragment fragment = new NestedStoreConsoleFragment();
        Bundle args = new Bundle();
        args.putInt(STOREID, storeId);
        args.putString(STORENAME, storeName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            storeId = getArguments().getInt(STOREID);
            storeName = getArguments().getString(STORENAME);
        }
        resources = getResources();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nested_store_console, container, false);

        System.out.println("Console StoreId: " + storeId);

        Consumer<StoreOpenStatusResponseDto> onStatusChecked = this::onBusinessStatusChecked;
        Runnable onError = this::onNetworkError;

        StoreRepository.getInstance().requestStoreBusinessStatus(TokenStore.getAuthToken(), storeId, onStatusChecked, onError);

        binding.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_storeManagementFragment_to_itemListFragment, bundle);
            }
        });

        binding.pointLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_storeManagementFragment_to_pointManageFragment, bundle);
            }
        });

        binding.storeOpenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreRepository.getInstance().requestStoreBusinessStatusUpdate(TokenStore.getAuthToken(), storeId, onStatusChecked, onError);
            }
        });

        binding.boardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_storeManagementFragment_to_boardListFragment, bundle);
            }
        });

        binding.reviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                bundle.putString(STORENAME, storeName);
                NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_storeManagementFragment_to_reviewListFragment, bundle);
            }
        });

        binding.accountingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_storeManagementFragment_to_accountingFragment, bundle);
            }
        });

        binding.couponLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_storeManagementFragment_to_couponListFragment, bundle);
            }
        });

        return binding.getRoot();
    }

    private void onBusinessStatusChecked(StoreOpenStatusResponseDto dto) {
        boolean result = dto.getOpenStatus();

        if (result) {
            binding.storeOpenImg.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_pause_24));
            binding.storeOpenText.setText("영업 중지");
        } else {
            binding.storeOpenImg.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_play_arrow_24));
            binding.storeOpenText.setText("영업 재개");
        }
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_global_networkErrorDialog);
    }
}
