package com.sososhopping.merchant.store.register.view;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sososhopping.merchant.R;
import com.sososhopping.merchant.databinding.FragmentStoreRegisterBasicFormBinding;
import com.sososhopping.merchant.store.register.viewmodel.StoreRegisterViewModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreRegisterBasicFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreRegisterBasicFormFragment extends Fragment {

    FragmentStoreRegisterBasicFormBinding binding;
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    public StoreRegisterBasicFormFragment() {
        // Required empty public constructor
    }

    public static StoreRegisterBasicFormFragment newInstance() {
        return new StoreRegisterBasicFormFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_register_basic_form, container, false);

        StoreRegisterViewModel viewModel = new ViewModelProvider(requireActivity()).get(StoreRegisterViewModel.class);
        binding.setStoreRegisterViewModel(viewModel);

        viewModel.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_img));

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            try{
                                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                binding.mainImage.setImageBitmap(bitmap);
                                viewModel.setBitmap(bitmap);
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                });

        binding.selectMainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbumForResult();
            }
        });

        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.storeRegisterBasicNext) Navigation.findNavController(binding.getRoot()).navigate(R.id.action_storeRegisterBasicFormFragment_to_storeRegisterMetadataFormFragment);

                return true;
            }
        });

        binding.category.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.store_register_basic_spinner)){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                if(position == 0) {
                    TextView textview = (TextView) v;
                    ((TextView) v).setTextColor(getResources().getColor(R.color.gray));
                } else {
                    TextView textview = (TextView) v;
                    ((TextView) v).setTextColor(getResources().getColor(R.color.black));
                }

                return v;
            }
        });

        binding.category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setCategory((String)parent.getSelectedItem());
                System.out.println(viewModel.getCategory().getValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void openAlbumForResult() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        someActivityResultLauncher.launch(intent);
    }
}