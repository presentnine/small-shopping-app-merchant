package com.sososhopping.merchant.point.dto;

public class PointRuleRequestDto {
    boolean pointPolicyStatus;
    double saveRate;

    public PointRuleRequestDto(boolean pointPolicyStatus, double saveRate) {
        this.pointPolicyStatus = pointPolicyStatus;
        this.saveRate = saveRate;
    }
}
