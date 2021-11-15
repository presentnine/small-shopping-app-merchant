package com.sososhopping.merchant.item.view;

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
import com.sososhopping.merchant.databinding.ItemItemListBinding;
import com.sososhopping.merchant.databinding.ItemShopListBinding;
import com.sososhopping.merchant.item.model.ItemListModel;
import com.sososhopping.merchant.main.model.ShopListModel;

import java.util.List;

public class ItemListRecyclerViewAdapter extends RecyclerView.Adapter<ItemListRecyclerViewAdapter.ViewHolder>{

    private final List<ItemListModel> mValues;

    public ItemListRecyclerViewAdapter(List<ItemListModel> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getName());
        holder.mDescriptionView.setText(mValues.get(position).getDescription());
        holder.mUnitView.setText(mValues.get(position).getPurchaseUnit());
        holder.mUnitPriceView.setText(Integer.toString(mValues.get(position).getUnitPrice()));
        Glide.with(holder.itemView.getContext())
                .load(Uri.parse(mValues.get(position).getImgUrl()))
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitleView;
        public final TextView mDescriptionView;
        public final TextView mUnitView;
        public final TextView mUnitPriceView;
        public final ImageView mImage;
        public ItemListModel mItem;

        public ViewHolder(ItemItemListBinding binding) {
            super(binding.getRoot());
            mTitleView = binding.shopListItemTitle;
            mDescriptionView = binding.shopListItemDescription;
            mImage = binding.shopListItemImage;
            mUnitView = binding.itemListItemUnit;
            mUnitPriceView = binding.itemListItemUnitPrice;
        }
    }
}
