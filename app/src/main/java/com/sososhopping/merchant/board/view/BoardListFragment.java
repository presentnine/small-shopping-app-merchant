package com.sososhopping.merchant.board.view;

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
import com.sososhopping.merchant.board.model.BoardListModel;
import com.sososhopping.merchant.board.repository.BoardRepository;
import com.sososhopping.merchant.databinding.FragmentBoardListBinding;
import com.sososhopping.merchant.item.model.ItemListModel;
import com.sososhopping.merchant.item.repository.ItemRepository;
import com.sososhopping.merchant.item.view.ItemListRecyclerViewAdapter;

import java.util.List;
import java.util.function.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardListFragment extends Fragment {

    private static final String STOREID = "storeId";

    private int storeId;

    FragmentBoardListBinding binding;

    private final BoardRepository boardRepository = BoardRepository.getInstance();

    public BoardListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BoardListFragment.
     */
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

        Consumer<List<BoardListModel>> onItemListAcquired = this::onBoardListAcquired;
        Runnable onFailed = this::onNetworkError;

        boardRepository.requestBoardList(((MainActivity)getActivity()).getLoginToken(), storeId, onItemListAcquired, onFailed);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(STOREID, storeId);
                Navigation.findNavController(v).navigate(R.id.action_boardListFragment_to_boardRegisterFragment, bundle);
            }
        });

        return binding.getRoot();
    }

    private void onBoardListAcquired(List<BoardListModel> boardList) {
        binding.shopListRecyclerView.setAdapter(new BoardListRecyclerViewAdapter(boardList));
    }

    private void onNetworkError() {
        NavHostFragment.findNavController(this).navigate(R.id.action_global_networkErrorDialog);
    }
}