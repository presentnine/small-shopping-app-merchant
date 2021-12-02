package com.sososhopping.merchant.view.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentAccountingBinding;
import com.sososhopping.merchant.model.accounting.entity.AccountingList;
import com.sososhopping.merchant.model.accounting.repository.AccountingRepository;
import com.sososhopping.merchant.model.board.entity.BoardList;
import com.sososhopping.merchant.model.board.repository.BoardRepository;
import com.sososhopping.merchant.util.token.TokenStore;
import com.sososhopping.merchant.view.adapter.AccountingListRecyclerViewAdapter;
import com.sososhopping.merchant.view.adapter.BoardListRecyclerViewAdapter;
import com.sososhopping.merchant.viewmodel.AccountingListViewModel;
import com.sososhopping.merchant.viewmodel.SignupViewModel;

import java.util.List;
import java.util.function.Consumer;

public class AccountingFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    FragmentAccountingBinding binding;

    public AccountingFragment() {
        // Required empty public constructor
    }

    public static AccountingFragment newInstance(int storeId) {
        AccountingFragment fragment = new AccountingFragment();
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
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_accounting, container, false);

        AccountingListViewModel viewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.NewInstanceFactory()).get(AccountingListViewModel.class);
        binding.setAccountingListViewModel(viewModel);

        Consumer<List<AccountingList>> onItemListAcquired = this::onBoardListAcquired;
        Runnable onError = this::onNetworkError;

        AccountingRepository.getInstance().requestAccountingList(TokenStore.getAuthToken(), storeId, viewModel.getDateString(), onItemListAcquired, onError);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                Navigation.findNavController(v).navigate(R.id.action_accountingFragment_to_accountingFormDialog, bundle);
            }
        });

        binding.shopAccountingDatePrevButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                viewModel.toPrevMonth();
                binding.invalidateAll();
                AccountingRepository.getInstance().requestAccountingList(TokenStore.getAuthToken(), storeId, viewModel.getDateString(), onItemListAcquired, onError);
            }
        });

        binding.shopAccountingDateNextButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                viewModel.toNextMonth();
                binding.invalidateAll();
                AccountingRepository.getInstance().requestAccountingList(TokenStore.getAuthToken(), storeId, viewModel.getDateString(), onItemListAcquired, onError);
            }
        });

        getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                AccountingRepository.getInstance().requestAccountingList(TokenStore.getAuthToken(), storeId, viewModel.getDateString(), onItemListAcquired, onError);
            }
        });

        return binding.getRoot();
    }

    private void onBoardListAcquired(List<AccountingList> accountingList) {
        binding.shopListRecyclerView.setAdapter(new AccountingListRecyclerViewAdapter(accountingList));
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }
}