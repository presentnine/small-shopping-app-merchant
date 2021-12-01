package com.sososhopping.merchant.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentCouponListBinding;
import com.sososhopping.merchant.model.coupon.dto.response.CouponListResponseDto;
import com.sososhopping.merchant.model.coupon.entity.CouponList;
import com.sososhopping.merchant.model.coupon.repository.CouponRepository;
import com.sososhopping.merchant.view.adapter.CouponListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CouponListFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    FragmentCouponListBinding binding;

    public CouponListFragment() {

    }

    public static CouponListFragment newInstance(int storeId) {
        CouponListFragment fragment = new CouponListFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coupon_list, container, false);

        Consumer<CouponListResponseDto> onSuccess = this::onListAcquired;
        Runnable onError = this::onNetworkError;
        
        CouponRepository.getInstance().requestCouponList(((MainActivity)getActivity()).getLoginToken(), storeId, onSuccess, onError);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                Navigation.findNavController(v).navigate(R.id.action_couponListFragment_to_couponRegisterFragment, bundle);
            }
        });

        return binding.getRoot();
    }

    private void onListAcquired(CouponListResponseDto dto) {
        List<CouponListRecyclerViewAdapter.Item> items = new ArrayList<>();
        CouponListRecyclerViewAdapter.HeaderItem beingHeader = new CouponListRecyclerViewAdapter.HeaderItem("발행중인 쿠폰");
        if (dto.getBeing() != null){
            System.out.println(dto.getBeing().size());
            for (CouponList coupon : dto.getBeing()) {
                beingHeader.getDataItems().add(new CouponListRecyclerViewAdapter.DataItem(coupon));
            }
        }
        items.add(beingHeader);
        CouponListRecyclerViewAdapter.HeaderItem expectedHeader = new CouponListRecyclerViewAdapter.HeaderItem("발행 예정 쿠폰");
        if (dto.getExpected() != null) {
            System.out.println(dto.getExpected().size());
            for (CouponList coupon : dto.getExpected()) {
                expectedHeader.getDataItems().add(new CouponListRecyclerViewAdapter.DataItem(coupon));
            }
        }
        items.add(expectedHeader);
        binding.shopListRecyclerView.setAdapter(new CouponListRecyclerViewAdapter(items));
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }
}