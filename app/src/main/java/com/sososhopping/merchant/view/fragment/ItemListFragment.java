package com.sososhopping.merchant.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentItemListBinding;
import com.sososhopping.merchant.model.item.entity.ItemList;
import com.sososhopping.merchant.model.item.repository.ItemRepository;
import com.sososhopping.merchant.view.adapter.ItemListRecyclerViewAdapter;

import java.util.List;
import java.util.function.Consumer;

public class ItemListFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    FragmentItemListBinding binding;

    public ItemListFragment() {

    }

    public static ItemListFragment newInstance(int storeId) {
        ItemListFragment fragment = new ItemListFragment();
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
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_item_list, container, false);

        Consumer<List<ItemList>> onItemListAcquired = this::onItemListAcquired;
        Runnable onFailed = this::onNetworkError;

        ItemRepository.getInstance().requestItemList(((MainActivity)getActivity()).getLoginToken(), storeId, onItemListAcquired, onFailed);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                Navigation.findNavController(v).navigate(R.id.action_itemListFragment_to_itemRegisterFragment, bundle);
            }
        });

        return binding.getRoot();
    }

    private void onItemListAcquired(List<ItemList> itemList) {
        binding.shopListRecyclerView.setAdapter(new ItemListRecyclerViewAdapter(itemList));
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }
}
