package com.sososhopping.merchant.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.model.point.dto.request.PointRuleRequestDto;

public class PointRuleViewModel extends ViewModel {

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

    private PointRuleRequestDto toDto() {
        return new PointRuleRequestDto(usePoint.getValue(), Double.valueOf(pointRate.getValue()));
    }
}
