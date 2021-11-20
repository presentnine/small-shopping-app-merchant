package com.sososhopping.merchant.viewmodel;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sososhopping.merchant.model.store.dto.request.StoreBusinessDaysDto;
import com.sososhopping.merchant.model.store.dto.request.StoreMetadataDto;
import com.sososhopping.merchant.model.store.dto.request.StoreRegisterRequestDto;

import java.util.ArrayList;
import java.util.List;

public class StoreRegisterViewModel extends ViewModel {
    MutableLiveData<Bitmap> bitmap = new MutableLiveData<>();

    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<String> description = new MutableLiveData<>();
    MutableLiveData<String> phone = new MutableLiveData<>();

    MutableLiveData<String> category = new MutableLiveData<>();

    MutableLiveData<Boolean> openMonday = new MutableLiveData<>(false);
    MutableLiveData<String> openHourMonday = new MutableLiveData<>();
    MutableLiveData<String> closeHourMonday = new MutableLiveData<>();

    MutableLiveData<Boolean> openTuesday = new MutableLiveData<>(false);
    MutableLiveData<String> openHourTuesday = new MutableLiveData<>();
    MutableLiveData<String> closeHourTuesday = new MutableLiveData<>();

    MutableLiveData<Boolean> openWednesday = new MutableLiveData<>(false);
    MutableLiveData<String> openHourWednesday = new MutableLiveData<>();
    MutableLiveData<String> closeHourWednesday = new MutableLiveData<>();

    MutableLiveData<Boolean> openThursday = new MutableLiveData<>(false);
    MutableLiveData<String> openHourThursday = new MutableLiveData<>();
    MutableLiveData<String> closeHourThursday = new MutableLiveData<>();

    MutableLiveData<Boolean> openFriday = new MutableLiveData<>(false);
    MutableLiveData<String> openHourFriday = new MutableLiveData<>();
    MutableLiveData<String> closeHourFriday = new MutableLiveData<>();

    MutableLiveData<Boolean> openSaturday = new MutableLiveData<>(false);
    MutableLiveData<String> openHourSaturday = new MutableLiveData<>();
    MutableLiveData<String> closeHourSaturday = new MutableLiveData<>();

    MutableLiveData<Boolean> openSunday = new MutableLiveData<>(false);
    MutableLiveData<String> openHourSunday = new MutableLiveData<>();
    MutableLiveData<String> closeHourSunday = new MutableLiveData<>();

    MutableLiveData<String> openHourDetail = new MutableLiveData<>();

    MutableLiveData<Boolean> delivery = new MutableLiveData<>();
    MutableLiveData<Boolean> localCurrency = new MutableLiveData<>();

    MutableLiveData<String> businessNumber = new MutableLiveData<>();
    MutableLiveData<String> repName = new MutableLiveData<>();
    MutableLiveData<String> businessName = new MutableLiveData<>();
    MutableLiveData<String> businessOpenDate = new MutableLiveData<>();

    MutableLiveData<String> streetAddress = new MutableLiveData<>();
    MutableLiveData<String> detailedAddress = new MutableLiveData<>();

    MutableLiveData<String> lat = new MutableLiveData<>();
    MutableLiveData<String> lng = new MutableLiveData<>();

    public MutableLiveData<String> getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat.setValue(lat);
    }

    public MutableLiveData<String> getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng.setValue(lng);
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap.setValue(bitmap);
    }

    public MutableLiveData<String> getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category.setValue(category);
    }

    public MutableLiveData<Bitmap> getBitmap() {
        return bitmap;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public MutableLiveData<String> getPhone() {
        return phone;
    }

    public MutableLiveData<Boolean> getOpenMonday() {
        return openMonday;
    }

    public MutableLiveData<String> getOpenHourMonday() {
        return openHourMonday;
    }

    public MutableLiveData<String> getCloseHourMonday() {
        return closeHourMonday;
    }

    public MutableLiveData<Boolean> getOpenTuesday() {
        return openTuesday;
    }

    public MutableLiveData<String> getOpenHourTuesday() {
        return openHourTuesday;
    }

    public MutableLiveData<String> getCloseHourTuesday() {
        return closeHourTuesday;
    }

    public MutableLiveData<Boolean> getOpenWednesday() {
        return openWednesday;
    }

    public MutableLiveData<String> getOpenHourWednesday() {
        return openHourWednesday;
    }

    public MutableLiveData<String> getCloseHourWednesday() {
        return closeHourWednesday;
    }

    public MutableLiveData<Boolean> getOpenThursday() {
        return openThursday;
    }

    public MutableLiveData<String> getOpenHourThursday() {
        return openHourThursday;
    }

    public MutableLiveData<String> getCloseHourThursday() {
        return closeHourThursday;
    }

    public MutableLiveData<Boolean> getOpenFriday() {
        return openFriday;
    }

    public MutableLiveData<String> getOpenHourFriday() {
        return openHourFriday;
    }

    public MutableLiveData<String> getCloseHourFriday() {
        return closeHourFriday;
    }

    public MutableLiveData<Boolean> getOpenSaturday() {
        return openSaturday;
    }

    public MutableLiveData<String> getOpenHourSaturday() {
        return openHourSaturday;
    }

    public MutableLiveData<String> getCloseHourSaturday() {
        return closeHourSaturday;
    }

    public MutableLiveData<Boolean> getOpenSunday() {
        return openSunday;
    }

    public MutableLiveData<String> getOpenHourSunday() {
        return openHourSunday;
    }

    public MutableLiveData<String> getCloseHourSunday() {
        return closeHourSunday;
    }

    public MutableLiveData<String> getOpenHourDetail() {
        return openHourDetail;
    }

    public MutableLiveData<Boolean> getDelivery() {
        return delivery;
    }

    public MutableLiveData<Boolean> getLocalCurrency() {
        return localCurrency;
    }

    public MutableLiveData<String> getBusinessNumber() {
        return businessNumber;
    }

    public MutableLiveData<String> getRepName() {
        return repName;
    }

    public MutableLiveData<String> getBusinessName() {
        return businessName;
    }

    public MutableLiveData<String> getBusinessOpenDate() {
        return businessOpenDate;
    }

    public MutableLiveData<String> getStreetAddress() {
        return streetAddress;
    }

    public MutableLiveData<String> getDetailedAddress() {
        return detailedAddress;
    }

    private StoreRegisterRequestDto toDto() {
        return new StoreRegisterRequestDto(
                name.getValue(),
                description.getValue(),
                phone.getValue(),
                category.getValue(),
                openHourDetail.getValue(),
                localCurrency.getValue(),
                delivery.getValue(),
                streetAddress.getValue(),
                detailedAddress.getValue(),
                lat.getValue(),
                lng.getValue(),
                buildStoreBusinessDaysList(),
                buildStoreMetadataDto()
        );
    }

    private List<StoreBusinessDaysDto> buildStoreBusinessDaysList() {
        List<StoreBusinessDaysDto> daysDtos = new ArrayList<>();

        daysDtos.add(new StoreBusinessDaysDto("월", openMonday.getValue(), openMonday.getValue() == false ? openHourMonday.getValue() : null, openMonday.getValue() == false ? closeHourMonday.getValue() : null));
        daysDtos.add(new StoreBusinessDaysDto("화", openTuesday.getValue(), openTuesday.getValue() == false ? openHourTuesday.getValue() : null, openTuesday.getValue() == false ? closeHourTuesday.getValue() : null));
        daysDtos.add(new StoreBusinessDaysDto("수", openWednesday.getValue(), openWednesday.getValue() == false ? openHourWednesday.getValue() : null, openWednesday.getValue() == false ? closeHourWednesday.getValue() : null));
        daysDtos.add(new StoreBusinessDaysDto("목", openThursday.getValue(), openThursday.getValue() == false ? openHourThursday.getValue() : null, openThursday.getValue() == false ? closeHourThursday.getValue() : null));
        daysDtos.add(new StoreBusinessDaysDto("금", openFriday.getValue(), openFriday.getValue() == false ? openHourFriday.getValue() : null, openFriday.getValue() == false ? closeHourFriday.getValue() : null));
        daysDtos.add(new StoreBusinessDaysDto("토", openSaturday.getValue(), openSaturday.getValue() == false ? openHourSaturday.getValue() : null, openSaturday.getValue() == false ? closeHourSaturday.getValue() : null));
        daysDtos.add(new StoreBusinessDaysDto("일", openSunday.getValue(), openSunday.getValue() == false ? openHourSunday.getValue() : null, openSunday.getValue() == false ? closeHourSunday.getValue() : null));

        return daysDtos;
    }

    private StoreMetadataDto buildStoreMetadataDto() {
        return new StoreMetadataDto(
                businessNumber.getValue(),
                repName.getValue(),
                businessName.getValue(),
                businessOpenDate.getValue()
        );
    }
}
