package com.sososhopping.merchant.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.LocalDate;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AccountingListViewModel extends ViewModel {
    MutableLiveData<String> year = new MutableLiveData<>(Integer.toString(LocalDate.now().getYear()));
    MutableLiveData<String> month = new MutableLiveData<>(Integer.toString(LocalDate.now().getMonthValue()));

    public MutableLiveData<String> getYear() {
        return year;
    }

    public MutableLiveData<String> getMonth() {
        return month;
    }

    public String getDateString() {
        if (Integer.parseInt(month.getValue()) < 10) return year.getValue() + "/" + "0" + month.getValue();
        return year.getValue() + "/" + month.getValue();
    }

    public void toPrevMonth() {
        if (month.getValue().equals("1")) {
            month.setValue("12");
            year.setValue(Integer.toString(Integer.parseInt(year.getValue()) - 1));
        } else {
            month.setValue(Integer.toString(Integer.parseInt(month.getValue()) - 1));
        }
    }

    public void toNextMonth() {
        if (month.getValue().equals("12")) {
            month.setValue("1");
            year.setValue(Integer.toString(Integer.parseInt(year.getValue()) + 1));
        } else {
            month.setValue(Integer.toString(Integer.parseInt(month.getValue()) + 1));
        }
    }
}
