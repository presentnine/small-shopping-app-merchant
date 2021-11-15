package com.sososhopping.merchant.store.manage.view;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentNestedStoreConsoleBinding;
import com.sososhopping.merchant.store.manage.dto.StoreBusinessStatusDto;
import com.sososhopping.merchant.store.manage.repository.StoreBusinessStatusRepository;

import java.util.function.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NestedStoreConsoleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NestedStoreConsoleFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    FragmentNestedStoreConsoleBinding binding;

    Resources resources;

    public NestedStoreConsoleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NestedStoreConsoleFragment.
     */
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
        resources = getResources();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nested_store_console, container, false);

        Consumer<StoreBusinessStatusDto> onStatusChecked = this::onBusinessStatusChecked;

        StoreBusinessStatusRepository.getInstance().requestStoreBusinessStatus(((MainActivity)getActivity()).getLoginToken(), storeId, onStatusChecked);

        binding.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_storeManagementFragment_to_itemListFragment, bundle);
            }
        });

        binding.pointLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                NavHostFragment.findNavController(getParentFragment().getParentFragment()).navigate(R.id.action_storeManagementFragment_to_pointManageFragment, bundle);
            }
        });

        binding.storeOpenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreBusinessStatusRepository.getInstance().requestStoreBusinessStatusUpdate(((MainActivity)getActivity()).getLoginToken(), storeId, onStatusChecked);
            }
        });

        return binding.getRoot();
    }

    private void onBusinessStatusChecked(StoreBusinessStatusDto dto) {
        System.out.println(storeId);
        boolean result = dto.isBusinessStatus();

        if (result) {
            binding.storeOpenImg.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_pause_24));
            binding.storeOpenText.setText("영업 중지");
        } else {
            binding.storeOpenImg.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_play_arrow_24));
            binding.storeOpenText.setText("영업 재개");
        }
    }
}