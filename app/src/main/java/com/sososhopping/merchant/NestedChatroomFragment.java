package com.sososhopping.merchant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.sososhopping.merchant.databinding.FragmentNestedChatroomBinding;
import com.sososhopping.merchant.util.token.TokenStore;

import java.util.ArrayList;

public class NestedChatroomFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;
    private String firebaseToken;

    private RecyclerView chatRoomRecyclerView;
    private ChatroomAdapter adapter;
    private RecyclerView.LayoutManager chatRoomLayoutManager;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    private DatabaseReference storeChatRoomInforRef;

    ArrayList<Chatroom> chatroomList = new ArrayList<>();

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

        firebaseToken = TokenStore.getFirebaseToken();
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCustomToken(firebaseToken)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCustomToken:success");
                            Toast.makeText(getContext(), "Authentication success.",
                                    Toast.LENGTH_SHORT).show();

                            user = mAuth.getCurrentUser();
                            firebaseDatabase = FirebaseDatabase.getInstance();
                            ref = firebaseDatabase.getReference();
                            storeChatRoomInforRef = ref.child("StoreChatRoomInfor").child(Integer.toString(storeId)).getRef();
                            storeChatRoomInforRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot data : snapshot.getChildren()) {
                                        Chatroom chatroom = data.getValue(Chatroom.class);
                                        chatroom.setChatroomId(data.getKey());
                                        chatroomList.add(chatroom);
                                    }

                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            setOnline();

                            //FCMID 연결 후 등록
                            FirebaseMessaging.getInstance().getToken()
                                    .addOnCompleteListener(new OnCompleteListener<String>() {
                                        @Override
                                        public void onComplete(@NonNull Task<String> task) {
                                            if (!task.isSuccessful()) {
                                                Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                                                return;
                                            }

                                            // Get new FCM registration token
                                            ref.child("FcmId").child(user.getUid()).setValue(task.getResult());
                                        }
                                    });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentNestedChatroomBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nested_chatroom, container, false);

        chatRoomLayoutManager = new LinearLayoutManager(getContext());
        adapter = new ChatroomAdapter(getContext(), chatroomList, Integer.toString(storeId));

        chatRoomRecyclerView = binding.chatRoomRecyclerView;
        chatRoomRecyclerView.setLayoutManager(chatRoomLayoutManager);
        chatRoomRecyclerView.scrollToPosition(0);
        chatRoomRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onStop() {
        super.onStop();
        setOffline();
    }

    private void setOnline() {
        ref.child("User").child(user.getUid()).child("connection").setValue(true);
    }

    private void setOffline() {
        ref.child("User").child(this.user.getUid()).child("connection").setValue(false);
        ref.child("User").child(this.user.getUid()).child("lastOnline").setValue(ServerValue.TIMESTAMP);
        mAuth.signOut();
    }
}