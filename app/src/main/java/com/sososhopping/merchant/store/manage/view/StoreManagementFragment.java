package com.sososhopping.merchant.store.manage.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentStoreManagementBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreManagementFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    public StoreManagementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StoreManagementFragment.
     */
    public static StoreManagementFragment newInstance(int storeId) {
        StoreManagementFragment fragment = new StoreManagementFragment();
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
        System.out.println(storeId);
        FragmentStoreManagementBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_management, container, false);

        NavHostFragment navHostFragment =
                (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        Bundle bundle = new Bundle();
        bundle.putInt(STOREID, storeId);
        navController.setGraph(R.navigation.nav_graph_nested_store_manage, bundle);

        return binding.getRoot();
    }
}