package com.sososhopping.merchant.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationBarView;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentOrderCheckBinding;

public class OrderCheckFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    private int currentItem;

    FragmentOrderCheckBinding binding;

    public OrderCheckFragment() {

    }

    public static OrderCheckFragment newInstance(int storeId) {
        OrderCheckFragment fragment = new OrderCheckFragment();
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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_check, container, false);

        NavHostFragment navHostFragment =
                (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        Bundle bundle = new Bundle();
        bundle.putInt(STOREID, storeId);
        navController.setGraph(R.navigation.nav_graph_nested_store_manage, bundle);

        currentItem = R.id.pending;
        binding.bottomNavigationView.getMenu().findItem(R.id.pending).setEnabled(false);

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.pending){
                    if (currentItem == R.id.delivery) {
                        Navigation.findNavController(binding.fragmentContainerView).navigate(R.id.action_nestedOrderPendingListFragment_to_nestedOrderDeliveryFragment);
                        binding.bottomNavigationView.getMenu().findItem(R.id.delivery).setEnabled(false);
                        binding.bottomNavigationView.getMenu().findItem(R.id.pending).setEnabled(true);
                        binding.bottomNavigationView.getMenu().findItem(R.id.pickup).setEnabled(true);
                        currentItem = R.id.delivery;
                    } else {
                        Navigation.findNavController(binding.fragmentContainerView).navigate(R.id.action_nestedOrderPendingListFragment_to_nestedOrderPickupFragment);
                        binding.bottomNavigationView.getMenu().findItem(R.id.delivery).setEnabled(true);
                        binding.bottomNavigationView.getMenu().findItem(R.id.pending).setEnabled(true);
                        binding.bottomNavigationView.getMenu().findItem(R.id.pickup).setEnabled(false);
                        currentItem = R.id.pickup;
                    }
                } else if (item.getItemId() == R.id.pickup){
                    if (currentItem == R.id.pending) {
                        ;
                    } else {
                        ;
                    }
                } else {
                    if (currentItem == R.id.pickup) {
                        ;
                    } else {
                        ;
                    }
                }
                return true;
            }
        });

        return binding.getRoot();
    }
}