package com.sososhopping.merchant;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.databinding.FragmentNestedChatroomBinding;

public class NestedChatroomFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    public NestedChatroomFragment() {

    }

    public static NestedChatroomFragment newInstance(int storeId) {
        NestedChatroomFragment fragment = new NestedChatroomFragment();
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
        System.out.println("Chatroom StoreId: " + storeId);
        FragmentNestedChatroomBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nested_chatroom, container, false);

        return binding.getRoot();
    }
}