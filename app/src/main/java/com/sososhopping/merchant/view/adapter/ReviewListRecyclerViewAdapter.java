package com.sososhopping.merchant.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sososhopping.merchant.databinding.ItemReviewListBinding;
import com.sososhopping.merchant.model.review.Entity.ReviewList;

import java.util.List;

public class ReviewListRecyclerViewAdapter extends RecyclerView.Adapter<ReviewListRecyclerViewAdapter.ViewHolder>{

    private final List<ReviewList> mValues;

    public ReviewListRecyclerViewAdapter(List<ReviewList> mValues) {
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemReviewListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (holder.mItem != null) {
            holder.mUsernameView.setText(holder.mItem.getUsername());
            holder.mContentView.setText(holder.mItem.getContent());
            holder.mCreatedAt.setText(holder.mItem.getCreatedAt());
            holder.mRatingBar.setRating((float) (holder.mItem.getScore()));
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mUsernameView;
        public final TextView mContentView;
        public final TextView mCreatedAt;
        public final RatingBar mRatingBar;
        public ReviewList mItem;

        public ViewHolder(ItemReviewListBinding binding) {
            super(binding.getRoot());
            mUsernameView = binding.reviewListUserName;
            mContentView = binding.reviewListReviewContent;
            mCreatedAt = binding.reviewListReviewDate;
            mRatingBar = binding.reviewListRating;
        }
    }
}
