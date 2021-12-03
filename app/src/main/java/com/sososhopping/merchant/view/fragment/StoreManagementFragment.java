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
import com.sososhopping.merchant.databinding.FragmentStoreManagementBinding;

public class StoreManagementFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    private int current = 0;

    public StoreManagementFragment() {

    }

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

        binding.bottomNavigationView.getMenu().findItem(R.id.manageConsole).setEnabled(false);

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.manageConsole) {
                    binding.bottomNavigationView.getMenu().findItem(R.id.manageConsole).setEnabled(false);
                    binding.bottomNavigationView.getMenu().findItem(R.id.manageChat).setEnabled(true);
                    binding.bottomNavigationView.getMenu().findItem(R.id.manageSchedule).setEnabled(true);
                    if (current == 1) {
                        Navigation.findNavController(binding.fragmentContainerView).navigate(R.id.action_nestedChatroomFragment_to_nestedStoreConsoleFragment, bundle);
                    } else {
                        Navigation.findNavController(binding.fragmentContainerView).navigate(R.id.action_nestedOrderCalendarFragment_to_nestedStoreConsoleFragment, bundle);
                    }
                    current = 0;
                } else if (item.getItemId() == R.id.manageChat) {
                    binding.bottomNavigationView.getMenu().findItem(R.id.manageConsole).setEnabled(true);
                    binding.bottomNavigationView.getMenu().findItem(R.id.manageChat).setEnabled(false);
                    binding.bottomNavigationView.getMenu().findItem(R.id.manageSchedule).setEnabled(true);
                    if (current == 0) {
                        Navigation.findNavController(binding.fragmentContainerView).navigate(R.id.action_nestedStoreConsoleFragment_to_nestedChatroomFragment, bundle);
                    } else {
                        Navigation.findNavController(binding.fragmentContainerView).navigate(R.id.action_nestedOrderCalendarFragment_to_nestedChatroomFragment, bundle);
                    }
                    current = 1;
                } else {
                    binding.bottomNavigationView.getMenu().findItem(R.id.manageConsole).setEnabled(true);
                    binding.bottomNavigationView.getMenu().findItem(R.id.manageChat).setEnabled(true);
                    binding.bottomNavigationView.getMenu().findItem(R.id.manageSchedule).setEnabled(false);
                    if (current == 0) {
                        Navigation.findNavController(binding.fragmentContainerView).navigate(R.id.action_nestedStoreConsoleFragment_to_nestedOrderCalendarFragment, bundle);
                    } else {
                        Navigation.findNavController(binding.fragmentContainerView).navigate(R.id.action_nestedChatroomFragment_to_nestedOrderCalendarFragment, bundle);
                    }
                    current = 2;
                }
                return true;
            }
        });

        return binding.getRoot();
    }
}
