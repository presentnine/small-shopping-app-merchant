package com.sososhopping.merchant.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.model.coupon.dto.request.CouponRegisterRequestDto;
import com.sososhopping.merchant.model.coupon.repository.CouponRepository;

public class CouponRegisterViewModel extends ViewModel {
    MutableLiveData<String> couponType = new MutableLiveData<>("FIX");
    MutableLiveData<String> couponName = new MutableLiveData<>();
    MutableLiveData<String> couponAmount = new MutableLiveData<>();
    MutableLiveData<String> couponMinPrice = new MutableLiveData<>();
    MutableLiveData<String> couponExpiry = new MutableLiveData<>();
    MutableLiveData<String> couponQuantity = new MutableLiveData<>();
    MutableLiveData<String> couponIssuedStart = new MutableLiveData<>();
    MutableLiveData<String> couponIssuedEnd = new MutableLiveData<>();

    public MutableLiveData<String> getCouponType() {
        return couponType;
    }

    public MutableLiveData<String> getCouponName() {
        return couponName;
    }

    public MutableLiveData<String> getCouponAmount() {
        return couponAmount;
    }

    public MutableLiveData<String> getCouponMinPrice() {
        return couponMinPrice;
    }

    public MutableLiveData<String> getCouponExpiry() {
        return couponExpiry;
    }

    public MutableLiveData<String> getCouponQuantity() {
        return couponQuantity;
    }

    public MutableLiveData<String> getCouponIssuedStart() {
        return couponIssuedStart;
    }

    public MutableLiveData<String> getCouponIssuedEnd() {
        return couponIssuedEnd;
    }

    public void setCouponType(String couponType) {
        this.couponType.setValue(couponType);
    }

    public void requestRegister(String token, int storeId) {
        CouponRepository.getInstance().requestRegister(token, storeId, this.toDto());
    }

    private CouponRegisterRequestDto toDto() {
        return new CouponRegisterRequestDto(
                couponName.getValue(),
                Integer.parseInt(couponQuantity.getValue()),
                Integer.parseInt(couponMinPrice.getValue()),
                couponIssuedStart.getValue(),
                couponIssuedEnd.getValue(),
                couponExpiry.getValue(),
                couponType.getValue(),
                couponType.getValue().equals("RATE") ? Double.parseDouble(couponAmount.getValue()) : 0,
                couponType.getValue().equals("PRICE") ? Integer.parseInt(couponAmount.getValue()) : 0
        );
    }
}
