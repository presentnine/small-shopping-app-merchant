package com.sososhopping.merchant.account.signup.repository;

import com.sososhopping.merchant.account.signup.dto.EmailDupCheckRequestDto;
import com.sososhopping.merchant.account.signup.dto.SignupRequestDto;
import com.sososhopping.merchant.account.signup.service.SignupService;
import com.sososhopping.merchant.common.retrofit.ApiServiceFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupRepository {
    private static SignupRepository instance;
    private final SignupService signupService;

    private SignupRepository() {
        this.signupService = ApiServiceFactory.createService(SignupService.class);
    }

    public static synchronized SignupRepository getInstance() {
        if(instance == null) {
            instance = new SignupRepository();
        }

        return instance;
    }

    public void requestEmailDuplicationCheck(EmailDupCheckRequestDto dto,
                                             Runnable onNotDuplicated,
                                             Runnable onDuplicated,
                                             Runnable onFailed) {
        signupService.requestEmailDuplicationCheck(dto).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 200:
                        onNotDuplicated.run();
                        break;
                    case 409:
                        onDuplicated.run();
                        break;
                    default:
                        onFailed.run();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onFailed.run();
            }
        });
    }

    public void requestSignup(SignupRequestDto dto,
                              Runnable onSuccess,
                              Runnable onFailed){
        signupService.requestSignup(dto).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 201) onSuccess.run();
                else onFailed.run();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onFailed.run();
            }
        });
    }
}
