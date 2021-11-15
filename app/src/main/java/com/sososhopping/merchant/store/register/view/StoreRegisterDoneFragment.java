package com.sososhopping.merchant.store.register.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentStoreRegisterDoneBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreRegisterDoneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreRegisterDoneFragment extends Fragment {

    public StoreRegisterDoneFragment() {
        // Required empty public constructor
    }

    public static StoreRegisterDoneFragment newInstance() {
        return new StoreRegisterDoneFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentStoreRegisterDoneBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_register_done, container, false);

        binding.signupDoneOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });

        return binding.getRoot();
    }
}