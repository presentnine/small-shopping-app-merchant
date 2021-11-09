package com.sososhopping.merchant.store.register.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreRegisterMetadataFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreRegisterMetadataFormFragment extends Fragment {

    public StoreRegisterMetadataFormFragment() {
        // Required empty public constructor
    }

    public static StoreRegisterMetadataFormFragment newInstance(String param1, String param2) {
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
        return inflater.inflate(R.layout.fragment_store_register_metadata_form, container, false);
    }
}