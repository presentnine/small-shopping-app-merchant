package com.sososhopping.merchant.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.R;


public class NestedOrderCalendarFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    public NestedOrderCalendarFragment() {

    }

    public static NestedOrderCalendarFragment newInstance(int storeId) {
        NestedOrderCalendarFragment fragment = new NestedOrderCalendarFragment();
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
        return inflater.inflate(R.layout.fragment_nested_order_calendar, container, false);
    }
}