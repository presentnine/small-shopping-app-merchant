package com.sososhopping.merchant;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.sososhopping.merchant.view.adapter.ItemListRecyclerViewAdapter;

import java.util.ArrayList;

public class ChatroomAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Chatroom> chatroomList = new ArrayList<>();
    String storeId;

    public ChatroomAdapter(Context context, ArrayList<Chatroom> chatroomList, String storeId) {
        this.context = context;
        this.chatroomList = chatroomList;
        this.storeId = storeId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chatroom_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).chatroomItemCustomerName.setText(chatroomList.get(position).customerName + " 님");
    }

    @Override
    public int getItemCount() {
        return chatroomList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView chatroomItemCustomerName;

        public ViewHolder(View v) {
            super(v);
            this.chatroomItemCustomerName = v.findViewById(R.id.chatroomItemCustomerName);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Chatroom chatroom = chatroomList.get(pos);
                        Bundle bundle = new Bundle();
                        bundle.putString("storeId", storeId);
                        bundle.putString("chatroomId", chatroom.chatroomId);
                        bundle.putString("userName", chatroom.customerName);
                        Navigation.findNavController((View) (v.getParent().getParent().getParent().getParent().getParent().getParent())).navigate(R.id.action_storeManagementFragment_to_conversationFragment, bundle);
                    }
                }
            });
        }
    }
}
