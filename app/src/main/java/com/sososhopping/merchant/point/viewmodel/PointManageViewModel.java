package com.sososhopping.merchant.point.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.point.dto.PointRuleRequestDto;
import com.sososhopping.merchant.point.repository.PointRepository;

public class PointManageViewModel extends ViewModel {

    MutableLiveData<Boolean> usePoint = new MutableLiveData<>(false);
    MutableLiveData<String> pointRate = new MutableLiveData<>();

    public MutableLiveData<Boolean> getUsePoint() {
        return usePoint;
    }

    public void setUsePoint(boolean usePoint) {
        this.usePoint.setValue(usePoint);
    }

    public MutableLiveData<String> getPointRate() {
        return pointRate;
    }

    public void setPointRate(double pointRate) {
        this.pointRate.setValue(Double.toString(pointRate));
    }

    public void requestUpdate(String token, int storeId){
        PointRepository.getInstance().requestPointRuleUpdate(token, storeId, this.toDto());
    }

    private PointRuleRequestDto toDto() {
        return new PointRuleRequestDto(usePoint.getValue(), Double.valueOf(pointRate.getValue()));
    }
}
