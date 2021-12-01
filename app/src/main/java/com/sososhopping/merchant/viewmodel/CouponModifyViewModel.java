package com.sososhopping.merchant.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.model.coupon.dto.request.CouponModifyRequestDto;
import com.sososhopping.merchant.model.coupon.repository.CouponRepository;

public class CouponModifyViewModel extends ViewModel {

    private final MutableLiveData<String> userPhone = new MutableLiveData<>();
    private final MutableLiveData<String> couponCode = new MutableLiveData<>();

    public MutableLiveData<String> getUserPhone() {
        return userPhone;
    }

    public MutableLiveData<String> getCouponCode() {
        return couponCode;
    }

    public void requestCouponCheck(String token, int storeId) {
        CouponRepository.getInstance().requestCouponCheck(token, storeId, userPhone.getValue(), couponCode.getValue());
    }

    public void requestCouponModify(String token, int storeId) {
        CouponRepository.getInstance().requestCouponModify(token, storeId, this.toDto());
    }

    private CouponModifyRequestDto toDto() {
        return new CouponModifyRequestDto(this.userPhone.getValue(), this.couponCode.getValue());
    }
}
