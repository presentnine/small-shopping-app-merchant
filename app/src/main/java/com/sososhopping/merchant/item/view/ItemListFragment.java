package com.sososhopping.merchant.item.view;

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
import com.sososhopping.merchant.item.model.ItemListModel;
import com.sososhopping.merchant.item.repository.ItemRepository;
import com.sososhopping.merchant.item.service.ItemService;
import com.sososhopping.merchant.main.model.ShopListModel;

import java.util.List;
import java.util.function.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemListFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    FragmentItemListBinding binding;

    private final ItemRepository itemRepository = ItemRepository.getInstance();

    public ItemListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ItemListFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        Consumer<List<ItemListModel>> onItemListAcquired = this::onItemListAcquired;
        Runnable onFailed = this::onNetworkError;

        itemRepository.requestMyShopList(((MainActivity)getActivity()).getLoginToken(), storeId, onItemListAcquired, onFailed);

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

    private void onItemListAcquired(List<ItemListModel> itemList) {
        binding.shopListRecyclerView.setAdapter(new ItemListRecyclerViewAdapter(itemList));
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }
}