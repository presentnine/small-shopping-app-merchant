package com.sososhopping.merchant.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sososhopping.merchant.databinding.ItemAccountingListBinding;
import com.sososhopping.merchant.model.accounting.entity.AccountingList;

import java.util.List;

public class AccountingListRecyclerViewAdapter extends RecyclerView.Adapter<AccountingListRecyclerViewAdapter.ViewHolder> {

    private final List<AccountingList> mValues;

    public AccountingListRecyclerViewAdapter(List<AccountingList> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemAccountingListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mAmount.setText(Integer.toString(holder.mItem.getAmount()));
        holder.mTitleView.setText(holder.mItem.getMemo());
        holder.mCreatedAt.setText(holder.mItem.getDateTime());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mAmount;
        public final TextView mTitleView;
        public final TextView mCreatedAt;
        public AccountingList mItem;

        public ViewHolder(ItemAccountingListBinding binding) {
            super(binding.getRoot());
            mTitleView = binding.Title;
            mCreatedAt = binding.createdAt;
            mAmount = binding.amount;
        }
    }
}
