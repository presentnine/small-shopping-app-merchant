package com.sososhopping.merchant.store.manage.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentStoreRegisterRejectedBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreRegisterRejectedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreRegisterRejectedFragment extends Fragment {


    public StoreRegisterRejectedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StoreRegisterRejectedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreRegisterRejectedFragment newInstance(String param1, String param2) {
        return new StoreRegisterRejectedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentStoreRegisterRejectedBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_register_rejected, container, false);

        binding.signupDoneOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });

        return binding.getRoot();
    }
}