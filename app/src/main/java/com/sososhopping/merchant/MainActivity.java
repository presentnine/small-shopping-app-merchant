package com.sososhopping.merchant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.sososhopping.merchant.databinding.ActivityMainBinding;
import com.sososhopping.merchant.util.token.TokenStore;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    public FirebaseAuth mAuth;
    public FirebaseUser user;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference ref;
    public boolean afterLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //파이어베이스 재인증 및 사용자 온라인 설정
        if (afterLogin == true) {
            user = mAuth.getCurrentUser();
            if (user == null) {
                mAuth.signInWithCustomToken(TokenStore.getFirebaseToken())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user = mAuth.getCurrentUser();
                                    ref.child("User").child(user.getUid()).child("connection").setValue(true);
                                }
                            }
                        });
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        //오프라인 설정
        if (afterLogin == true) {
            ref.child("User").child(this.user.getUid()).child("connection").setValue(false);
            ref.child("User").child(this.user.getUid()).child("lastOnline").setValue(ServerValue.TIMESTAMP);
            mAuth.signOut();
        }
    }

    //채팅방 생성
    public String makeChatroom(String storeId, String userId, String customerName, String storeName) {
        String userUid = "U" + userId;
        String ownerUid = user.getUid();
        String chatRoomId = storeId + "@" +  ownerUid + "@" + userUid;

        ChatroomInfor chatRoomInfor = new ChatroomInfor(customerName, storeName, chatRoomId);
        ref.child("ChatroomInfor")
                .child(userUid)
                .child(chatRoomId)
                .setValue(chatRoomInfor);

        ref.child("ChatroomInfor")
                .child(storeId)
                .child(chatRoomId)
                .setValue(chatRoomInfor);

        ChatroomUsers chatRoomUserInfor = new ChatroomUsers(userUid, ownerUid);
        ref.child("ChatroomUsers")
                .child(chatRoomId)
                .setValue(chatRoomUserInfor);

        return chatRoomId;
    }
}
