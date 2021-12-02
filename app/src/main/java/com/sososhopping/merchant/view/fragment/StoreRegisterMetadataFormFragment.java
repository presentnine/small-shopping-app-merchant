package com.sososhopping.merchant.view.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentStoreRegisterMetadataFormBinding;
import com.sososhopping.merchant.util.token.TokenStore;
import com.sososhopping.merchant.viewmodel.StoreRegisterViewModel;

import java.io.IOException;
import java.util.List;

public class StoreRegisterMetadataFormFragment extends Fragment {

    FragmentStoreRegisterMetadataFormBinding binding;

    public StoreRegisterMetadataFormFragment() {

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

        NavController navController = NavHostFragment.findNavController(this);

        ViewModelProvider viewModelProvider = new ViewModelProvider(navController.getViewModelStoreOwner(R.id.navigationStoreRegister));

        StoreRegisterViewModel viewModel = viewModelProvider.get(StoreRegisterViewModel.class);
        binding.setStoreRegisterViewModel(viewModel);

        System.out.println(viewModel.getName().getValue());

        Runnable onSuccess = this::navigateToNext;
        Runnable onError = this::onNetworkError;

        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.storeRegisterMetadataRegister) {
                    viewModel.requestRegister(TokenStore.getAuthToken(), onSuccess, onError);
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
                    if(addressList.size() == 0) {
                        binding.shopAddress.setErrorEnabled(true);
                        binding.shopAddress.setError("주소를 확인할 수 없습니다. 올바른 주소인지 확인해주세요.");
                        binding.shopDetailedAddress.setErrorEnabled(true);
                        binding.shopDetailedAddress.setError("주소를 확인할 수 없습니다. 올바른 주소인지 확인해주세요.");
                    }
                    else {
                        binding.shopAddressButton.setEnabled(false);
                        binding.shopAddressButton.setText("확인 완료");
                        binding.shopAddressButton.setTextColor(getResources().getColor(R.color.positive));
                        binding.shopAddress.setError(null);
                        binding.shopAddress.setErrorEnabled(false);
                        binding.shopAddress.setEnabled(false);
                        binding.shopDetailedAddress.setEnabled(false);
                        binding.shopDetailedAddress.setError(null);
                        binding.shopDetailedAddress.setErrorEnabled(false);
                        viewModel.setLng(String.valueOf(addressList.get(0).getLongitude()));
                        viewModel.setLat(String.valueOf(addressList.get(0).getLatitude()));
                    }
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

    private void navigateToNext() {
        NavHostFragment.findNavController(this).navigate(R.id.action_storeRegisterMetadataFormFragment_to_storeRegisterDoneFragment);
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }
}
