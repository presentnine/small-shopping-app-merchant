package com.sososhopping.merchant.main.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.ItemShopListBinding;
import com.sososhopping.merchant.main.model.ShopListModel;

import java.util.List;

public class ShopListRecyclerViewAdapter extends RecyclerView.Adapter<ShopListRecyclerViewAdapter.ViewHolder>{

    private final List<ShopListModel> mValues;

    public ShopListRecyclerViewAdapter(List<ShopListModel> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemShopListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getName());
        holder.mDescriptionView.setText(mValues.get(position).getDescription());
        if (mValues.get(position).getImageUrl() != null){
            Glide.with(holder.itemView.getContext())
                    .load(Uri.parse(mValues.get(position).getImageUrl()))
                    .into(holder.mImage);
        }
        switch (mValues.get(position).getStatus()){
            case "ACTIVE": {
                Glide.with(holder.itemView.getContext())
                        .load(R.drawable.ic_baseline_check_circle_24)
                        .into(holder.mVerified);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("storeId", holder.mItem.getId());
                        Navigation.findNavController((View) (v.getParent().getParent().getParent().getParent().getParent().getParent())).navigate(R.id.action_mainFragment_to_storeManagementFragment, bundle);
                    }
                });
                break;
            }
            case "PENDING": {
                Glide.with(holder.itemView.getContext())
                        .load(R.drawable.ic_baseline_hourglass_empty_24)
                        .into(holder.mVerified);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController((View) (v.getParent().getParent().getParent().getParent().getParent().getParent())).navigate(R.id.action_mainFragment_to_storeRegisterPendingFragment);
                    }
                });
                break;
            }
            case "REJECT": {
                Glide.with(holder.itemView.getContext())
                        .load(R.drawable.ic_baseline_remove_circle_outline_24)
                        .into(holder.mVerified);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController((View) (v.getParent().getParent().getParent().getParent().getParent().getParent())).navigate(R.id.action_mainFragment_to_storeRegisterRejectedFragment);
                    }
                });
                break;
            }
            case "SUSPEND": {
                Glide.with(holder.itemView.getContext())
                        .load(R.drawable.ic_baseline_not_interested_24)
                        .into(holder.mVerified);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController((View) (v.getParent().getParent().getParent().getParent().getParent().getParent())).navigate(R.id.action_mainFragment_to_storeRegisterDeniedFragment);
                    }
                });
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitleView;
        public final TextView mDescriptionView;
        public final ImageView mImage;
        public final ImageView mVerified;
        public ShopListModel mItem;

        public ViewHolder(ItemShopListBinding binding) {
            super(binding.getRoot());
            mTitleView = binding.shopListItemTitle;
            mDescriptionView = binding.shopListItemDescription;
            mImage = binding.shopListItemImage;
            mVerified = binding.shopListItemVerified;
        }
    }
}
