package com.sososhopping.merchant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sososhopping.merchant.databinding.FragmentConversationBinding;
import com.sososhopping.merchant.util.token.TokenStore;

import java.util.ArrayList;

public class ConversationFragment extends Fragment {

    private static final String STOREID = "storeId";
    private static final String CHATROOMID = "chatroomId";
    private static final String USERNAME = "userName";

    private String storeId;
    private String chatroomId;
    private String userName;
    private String ownerUid;

    private RecyclerView chatRecyclerView;
    private ChatAdapter adapter;
    private RecyclerView.LayoutManager chatLayoutManager;
    private EditText chatroomInputEditText;
    private Button chatroomInputSendButton;
    private TextView chatroomToolbarTextView;

    ArrayList<Chat> chatList = new ArrayList<>();

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;

    public ConversationFragment() {
    }

    public static ConversationFragment newInstance(String storeId, String chatroomId, String userName) {
        ConversationFragment fragment = new ConversationFragment();
        Bundle args = new Bundle();
        args.putString(STOREID, storeId);
        args.putString(CHATROOMID, chatroomId);
        args.putString(USERNAME, userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            storeId = getArguments().getString(STOREID);
            chatroomId = getArguments().getString(CHATROOMID);
            userName = getArguments().getString(USERNAME);
        }

        ownerUid = chatroomId.split("@")[1];
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCustomToken(TokenStore.getFirebaseToken())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "signInWithCustomToken:success");
                            Toast.makeText(getContext(), "Authentication success.",
                                    Toast.LENGTH_SHORT).show();

                            user = mAuth.getCurrentUser();
                            firebaseDatabase = FirebaseDatabase.getInstance();
                            ref = firebaseDatabase.getReference();
                            ref.child("ChatRoomMessage").child(chatroomId).addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    Chat chat = snapshot.getValue(Chat.class);
                                    chatList.add(chat);
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                }

                                @Override
                                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentConversationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_conversation, container, false);

        chatroomInputEditText = binding.chatroomInputEditText;
        chatroomInputSendButton = binding.chatroomInputSendButton;
        chatroomToolbarTextView = binding.chatroomToolbarTextView;
        chatroomToolbarTextView.setText(userName+ "님");

        chatLayoutManager = new LinearLayoutManager(getContext());
        adapter = new ChatAdapter(getContext(), userName, ownerUid, chatList);

        chatRecyclerView = binding.chatroom;
        chatRecyclerView.setLayoutManager(chatLayoutManager);
        chatRecyclerView.scrollToPosition(0);
        chatRecyclerView.setAdapter(adapter);

        chatroomInputSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chatroomInputEditText.getText().toString().equals("")) {
                    Chat chat = new Chat(ownerUid, "text", chatroomInputEditText.getText().toString(), null);
                    ref.child("ChatRoomMessage").child(chatroomId).push().setValue(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            chatroomInputEditText.setText("");
                        }
                    });
                }
            }
        });

        return binding.getRoot();
    }
}