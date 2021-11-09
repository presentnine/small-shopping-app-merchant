package com.sososhopping.merchant.main.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.NestedFragmentSettingsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NestedSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NestedSettingsFragment extends Fragment {

    NestedFragmentSettingsBinding binding;

    public NestedSettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NestedSettingsFragment.
     */
    public static NestedSettingsFragment newInstance() {
        return new NestedSettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.nested_fragment_settings, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}