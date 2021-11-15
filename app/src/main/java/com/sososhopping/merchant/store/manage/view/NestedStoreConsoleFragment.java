package com.sososhopping.merchant.store.manage.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentNestedStoreConsoleBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NestedStoreConsoleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NestedStoreConsoleFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    public NestedStoreConsoleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NestedStoreConsoleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NestedStoreConsoleFragment newInstance(int storeId) {
        NestedStoreConsoleFragment fragment = new NestedStoreConsoleFragment();
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
        FragmentNestedStoreConsoleBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nested_store_console, container, false);

        binding.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_storeManagementFragment_to_itemListFragment, bundle);
            }
        });

        return binding.getRoot();
    }
}