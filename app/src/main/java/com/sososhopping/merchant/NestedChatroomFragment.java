package com.sososhopping.merchant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sososhopping.merchant.databinding.FragmentNestedChatroomBinding;

import java.util.ArrayList;

public class NestedChatroomFragment extends Fragment {

    private static final String STOREID = "storeId";
    private static final String CHATROOMINFOR = "ChatroomInfor";

    private int storeId;

    private RecyclerView chatRoomRecyclerView;
    private ChatroomAdapter adapter;
    private RecyclerView.LayoutManager chatRoomLayoutManager;

    private DatabaseReference ref;
    private DatabaseReference chatroomInforRef;

    ArrayList<ChatroomInfor> chatroomInforList;

    public NestedChatroomFragment() {

    }

    public static NestedChatroomFragment newInstance(int storeId) {
        NestedChatroomFragment fragment = new NestedChatroomFragment();
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

        ref = ((MainActivity) getActivity()).ref;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentNestedChatroomBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nested_chatroom, container, false);

        chatroomInforList = new ArrayList<>();

        chatRoomLayoutManager = new LinearLayoutManager(getContext());
        adapter = new ChatroomAdapter(getContext(), chatroomInforList, Integer.toString(storeId));

        chatRoomRecyclerView = binding.chatRoomRecyclerView;
        chatRoomRecyclerView.setLayoutManager(chatRoomLayoutManager);
        chatRoomRecyclerView.scrollToPosition(0);
        chatRoomRecyclerView.setAdapter(adapter);

        setChatroomList();

        return binding.getRoot();
    }

    private void setChatroomList() {
        chatroomInforRef = ref.child(CHATROOMINFOR).child(Integer.toString(storeId)).orderByChild("lastMessageTimestamp").getRef();
        chatroomInforRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    ChatroomInfor chatroomInfor = data.getValue(ChatroomInfor.class);
                    chatroomInforList.add(0, chatroomInfor);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}