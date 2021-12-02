package com.sososhopping.merchant.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentReviewListBinding;
import com.sososhopping.merchant.model.review.dto.response.ReviewListResponseDto;
import com.sososhopping.merchant.model.review.repository.ReviewRepository;
import com.sososhopping.merchant.util.token.TokenStore;
import com.sososhopping.merchant.view.adapter.ReviewListRecyclerViewAdapter;

import java.util.function.Consumer;

public class ReviewListFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    FragmentReviewListBinding binding;

    public ReviewListFragment() {

    }

    public static ReviewListFragment newInstance(int storeId) {
        ReviewListFragment fragment = new ReviewListFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_list, container, false);

        Consumer<ReviewListResponseDto> onReviewListAcquired = this::onReviewListAcquired;
        Runnable onError = this::onNetworkError;

        ReviewRepository.getInstance().requestReviewList(TokenStore.getAuthToken(), storeId, onReviewListAcquired, onError);

        return binding.getRoot();
    }

    private void onReviewListAcquired(ReviewListResponseDto dto) {
        binding.avgScore.setText(Double.toString(dto.getAverageScore()));
        binding.count.setText(Integer.toString(dto.getReviewCount()));
        if (dto.getReviewCount() != 0) {
            binding.recyclerView.setAdapter(new ReviewListRecyclerViewAdapter(dto.getReviewLists()));
        }
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }
}
