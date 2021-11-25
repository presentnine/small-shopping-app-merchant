package com.sososhopping.merchant.model.accounting.repository;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sososhopping.merchant.model.accounting.dto.request.AccountingRegisterRequestDto;
import com.sososhopping.merchant.model.accounting.entity.AccountingList;
import com.sososhopping.merchant.model.accounting.service.AccountingService;
import com.sososhopping.merchant.util.retrofit.factory.ApiServiceFactory;

import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountingRepository {

    private static AccountingRepository instance;
    private final AccountingService service;

    private AccountingRepository() {
        this.service = ApiServiceFactory.createService(AccountingService.class);
    }

    public static synchronized AccountingRepository getInstance() {
        if(instance == null) {
            instance = new AccountingRepository();
        }

        return instance;
    }

    public void requestAccountingList(String token, int storeId, String yearMonth, Consumer<List<AccountingList>> consumer, Runnable onError) {
        service.requestAccountingList(token, storeId, yearMonth).enqueue(new Callback<List<AccountingList>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<AccountingList>> call, Response<List<AccountingList>> response) {
                System.out.println(response.code());
                if(response.code() == 200) {
                    consumer.accept(response.body());
                } else {
                    onError.run();
                }
            }

            @Override
            public void onFailure(Call<List<AccountingList>> call, Throwable t) {
                onError.run();
            }
        });
    }

    public void requestAccountingRegister(String token,
                                     int storeId,
                                     AccountingRegisterRequestDto dto,
                                     Runnable onSuccess,
                                     Runnable onError) {
        service.requestAccountingRegister(token, storeId, dto).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                if(response.code() == 201) {
                    onSuccess.run();
                } else {
                    onError.run();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onError.run();
            }
        });
    }
}
