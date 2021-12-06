package com.sososhopping.merchant.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentMainBinding;
import com.sososhopping.merchant.util.token.TokenStore;

public class MainFragment extends Fragment {

    FragmentMainBinding binding;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        binding.bottomNavigationView.getMenu().findItem(R.id.mainShopList).setEnabled(false);
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mainShopList){
                    Navigation.findNavController(binding.fragmentContainerView).navigate(R.id.action_nestedSettingsFragment_to_nestedShopListFragment);
                    binding.bottomNavigationView.getMenu().findItem(R.id.mainShopList).setEnabled(false);
                    binding.bottomNavigationView.getMenu().findItem(R.id.mainSettings).setEnabled(true);
                } else{
                    Navigation.findNavController(binding.fragmentContainerView).navigate(R.id.action_nestedShopListFragment_to_nestedSettingsFragment);
                    binding.bottomNavigationView.getMenu().findItem(R.id.mainShopList).setEnabled(true);
                    binding.bottomNavigationView.getMenu().findItem(R.id.mainSettings).setEnabled(false);
                }
                return true;
            }
        });

        ((MainActivity) getActivity()).mAuth = FirebaseAuth.getInstance();
        ((MainActivity) getActivity()).mAuth.signInWithCustomToken(TokenStore.getFirebaseToken())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            ((MainActivity) getActivity()).user = ((MainActivity) getActivity()).mAuth.getCurrentUser();
                            ((MainActivity) getActivity()).afterLogin = true;
                            ((MainActivity) getActivity()).firebaseDatabase = FirebaseDatabase.getInstance();
                            ((MainActivity) getActivity()).ref = ((MainActivity) getActivity()).firebaseDatabase.getReference();

                            //FcmId 설정
                            FirebaseMessaging.getInstance().getToken()
                                    .addOnCompleteListener(new OnCompleteListener<String>() {
                                        @Override
                                        public void onComplete(@NonNull Task<String> task) {
                                            if (!task.isSuccessful()) {
                                                Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                                                return;
                                            }

                                            ((MainActivity) getActivity()).ref.child("FcmId").child(((MainActivity) getActivity()).user.getUid()).setValue(task.getResult());
                                        }
                                    });
                            
                            //온라인 설정
                            ((MainActivity) getActivity()).ref.child("User").child(((MainActivity) getActivity()).user.getUid()).child("connection").setValue(true);
                        }
                    }
                });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
