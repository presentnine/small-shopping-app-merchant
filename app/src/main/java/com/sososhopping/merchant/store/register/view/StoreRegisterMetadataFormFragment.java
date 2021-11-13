package com.sososhopping.merchant.store.register.view;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentStoreRegisterMetadataFormBinding;
import com.sososhopping.merchant.store.register.viewmodel.StoreRegisterViewModel;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreRegisterMetadataFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreRegisterMetadataFormFragment extends Fragment {

    FragmentStoreRegisterMetadataFormBinding binding;

    public StoreRegisterMetadataFormFragment() {
        // Required empty public constructor
    }

    public static StoreRegisterMetadataFormFragment newInstance() {
        return new StoreRegisterMetadataFormFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_register_metadata_form, container, false);

        StoreRegisterViewModel viewModel = new ViewModelProvider(requireActivity()).get(StoreRegisterViewModel.class);
        binding.setStoreRegisterViewModel(viewModel);
        binding.setLifecycleOwner(this.getViewLifecycleOwner());

        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.storeRegisterMetadataRegister) {
                    viewModel.requestRegister(((MainActivity)getActivity()).getLoginToken());
                }
                return true;
            }
        });

        binding.shopAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Geocoder geocoder = new Geocoder(getContext());
                List<Address> addressList;

                try{
                    System.out.println(viewModel.getStreetAddress().getValue());
                    addressList = geocoder.getFromLocationName(viewModel.getStreetAddress().getValue(), 1);
                    if(addressList.size() == 0) return;
                    System.out.println(addressList.get(0).getLatitude());
                    System.out.println(addressList.get(0).getLongitude());
                    viewModel.setLng(String.valueOf(addressList.get(0).getLongitude()));
                    viewModel.setLat(String.valueOf(addressList.get(0).getLatitude()));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}