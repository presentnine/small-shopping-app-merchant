package com.sososhopping.merchant.main.view;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.NestedFragmentShopListBinding;
import com.sososhopping.merchant.main.model.ShopListModel;
import com.sososhopping.merchant.main.repository.ShopListRepository;

import java.util.List;
import java.util.function.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NestedShopListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NestedShopListFragment extends Fragment {

    NestedFragmentShopListBinding binding;
    private final ShopListRepository shopListRepository = ShopListRepository.getInstance();

    public NestedShopListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    public static NestedShopListFragment newInstance() {
        return new NestedShopListFragment();
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

        Consumer<List<ShopListModel>> onShopListAcquired = this::onShopListAcquired;
        Runnable onFailed = this::onNetworkError;

        shopListRepository.requestMyShopList(((MainActivity)getActivity()).getLoginToken(), onShopListAcquired, onFailed);

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

    private void onShopListAcquired(List<ShopListModel> shopList) {
        binding.shopListRecyclerView.setAdapter(new ShopListRecyclerViewAdapter(shopList));
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_global_networkErrorDialog);
    }
}
