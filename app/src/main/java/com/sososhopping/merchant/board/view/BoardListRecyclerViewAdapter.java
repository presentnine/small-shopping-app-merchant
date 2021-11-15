package com.sososhopping.merchant.board.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sososhopping.merchant.board.model.BoardListModel;
import com.sososhopping.merchant.databinding.ItemBoardListBinding;
import java.util.List;

public class BoardListRecyclerViewAdapter extends RecyclerView.Adapter<BoardListRecyclerViewAdapter.ViewHolder>{

    private final List<BoardListModel> mValues;

    public BoardListRecyclerViewAdapter(List<BoardListModel> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBoardListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCategoryView.setText(holder.mItem.getWritingType().equals("EVENT") ? "이벤트" : "소식");
        holder.mTitleView.setText(holder.mItem.getTitle());
        holder.mDescriptionView.setText(holder.mItem.getContent());
        holder.mCreatedAt.setText(holder.mItem.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mCategoryView;
        public final TextView mTitleView;
        public final TextView mDescriptionView;
        public final TextView mCreatedAt;
        public BoardListModel mItem;

        public ViewHolder(ItemBoardListBinding binding) {
            super(binding.getRoot());
            mCategoryView = binding.category;
            mTitleView = binding.Title;
            mDescriptionView = binding.description;
            mCreatedAt = binding.createdAt;
        }
    }
}
