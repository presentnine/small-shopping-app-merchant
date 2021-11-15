package com.sososhopping.merchant.store.manage.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentStoreRegisterPendingBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreRegisterPendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreRegisterPendingFragment extends Fragment {

    public StoreRegisterPendingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StoreRegisterPendingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreRegisterPendingFragment newInstance(String param1, String param2) {
        return new StoreRegisterPendingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentStoreRegisterPendingBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_register_pending, container, false);

        binding.signupDoneOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });

        return binding.getRoot();
    }
}