package com.sososhopping.merchant.view.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.NestedFragmentShopListBinding;
import com.sososhopping.merchant.model.store.entity.StoreList;
import com.sososhopping.merchant.model.store.repository.StoreRepository;
import com.sososhopping.merchant.util.token.TokenStore;
import com.sososhopping.merchant.view.adapter.StoreListRecyclerViewAdapter;

import java.util.List;
import java.util.function.Consumer;

public class NestedStoreListFragment extends Fragment {

    NestedFragmentShopListBinding binding;

    public NestedStoreListFragment() {

    }

    public static NestedStoreListFragment newInstance() {
        return new NestedStoreListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.nested_fragment_shop_list, container, false);

        Consumer<List<StoreList>> onShopListAcquired = this::onShopListAcquired;
        Runnable onFailed = this::onNetworkError;

        StoreRepository.getInstance().requestStoreList(TokenStore.getAuthToken(), onShopListAcquired, onFailed);

        binding.shopListToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.shopListRegister) NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_mainFragment_to_storeRegisterBasicFormFragment);
                return true;
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onShopListAcquired(List<StoreList> shopList) {
        binding.shopListRecyclerView.setAdapter(new StoreListRecyclerViewAdapter(shopList));
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_global_networkErrorDialog);
    }
}
