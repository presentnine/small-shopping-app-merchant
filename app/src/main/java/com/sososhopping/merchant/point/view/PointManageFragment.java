package com.sososhopping.merchant.point.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentPointManageBinding;
import com.sososhopping.merchant.point.dto.PointRuleResponseDto;
import com.sososhopping.merchant.point.repository.PointRepository;
import com.sososhopping.merchant.point.viewmodel.PointInfoViewModel;
import com.sososhopping.merchant.point.viewmodel.PointManageViewModel;

import java.util.function.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PointManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PointManageFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    FragmentPointManageBinding binding;

    public PointManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PointManageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PointManageFragment newInstance(int storeId) {
        PointManageFragment fragment = new PointManageFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_point_manage, container, false);
        PointManageViewModel viewModel = new ViewModelProvider(requireActivity()).get(PointManageViewModel.class);
        binding.setPointManageViewModel(viewModel);

        Consumer<PointRuleResponseDto> onPointRuleChecked = this::onPointRuleChecked;
        PointRepository.getInstance().requestPointRule(((MainActivity)getActivity()).getLoginToken(), storeId, onPointRuleChecked);

        binding.pointHandleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                Navigation.findNavController(v).navigate(R.id.action_pointManageFragment_to_pointFormDialog, bundle);
            }
        });

        binding.pointRuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.requestUpdate(((MainActivity)getActivity()).getLoginToken(), storeId);
            }
        });

        return binding.getRoot();
    }

    private void onPointRuleChecked(PointRuleResponseDto dto) {
        binding.enablePoint.setChecked(dto.isPointPolicyStatus());
        binding.signupFormEmailLayout.getEditText().setText(Double.toString(dto.getSaveRate()));
    }
}