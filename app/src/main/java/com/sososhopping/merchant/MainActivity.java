package com.sososhopping.merchant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.sososhopping.merchant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    public FirebaseAuth mAuth;
    public FirebaseUser user;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference ref;
    public boolean isConnectWithFirebase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //온라인 설정
        if (isConnectWithFirebase == true) {
            ref.child("User").child(this.user.getUid()).child("connection").setValue(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        //오프라인 설정
        if (isConnectWithFirebase == true) {
            ref.child("User").child(this.user.getUid()).child("connection").setValue(false);
            ref.child("User").child(this.user.getUid()).child("lastOnline").setValue(ServerValue.TIMESTAMP);
            mAuth.signOut();
        }
    }
}
