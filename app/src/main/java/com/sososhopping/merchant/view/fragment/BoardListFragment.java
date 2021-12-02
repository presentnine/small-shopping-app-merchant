package com.sososhopping.merchant.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sososhopping.merchant.MainActivity;
import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentBoardListBinding;
import com.sososhopping.merchant.model.board.entity.BoardList;
import com.sososhopping.merchant.model.board.repository.BoardRepository;
import com.sososhopping.merchant.util.token.TokenStore;
import com.sososhopping.merchant.view.adapter.BoardListRecyclerViewAdapter;

import java.util.List;
import java.util.function.Consumer;

public class BoardListFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    FragmentBoardListBinding binding;

    public BoardListFragment() {

    }

    public static BoardListFragment newInstance(int storeId) {
        BoardListFragment fragment = new BoardListFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board_list, container, false);

        Consumer<List<BoardList>> onItemListAcquired = this::onBoardListAcquired;
        Runnable onError = this::onNetworkError;

        BoardRepository.getInstance().requestBoardList(TokenStore.getAuthToken(), storeId, onItemListAcquired, onError);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRegister();
            }
        });

        return binding.getRoot();
    }

    private void navigateToRegister() {
        Bundle bundle = new Bundle();
        bundle.putInt(STOREID, storeId);
        NavHostFragment.findNavController(this).navigate(R.id.action_boardListFragment_to_boardRegisterFragment, bundle);
    }

    private void onBoardListAcquired(List<BoardList> boardList) {
        binding.shopListRecyclerView.setAdapter(new BoardListRecyclerViewAdapter(boardList));
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }
}
