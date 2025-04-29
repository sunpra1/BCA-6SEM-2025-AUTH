package com.sunpra.auth.model;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("country")
    private String country;
    @SerializedName("region")
    private String region;
    @SerializedName("district")
    private String district;
    @SerializedName("address_line")
    private String addressLine;
    @SerializedName("isInsideRingroad")
    private boolean isInsideRingRoad;
    @SerializedName("house_no")
    private int houseNo;

    public Address(String country, String region, String district, String addressLine, boolean isInsideRingRoad, int houseNo) {
        this.country = country;
        this.region = region;
        this.district = district;
        this.addressLine = addressLine;
        this.isInsideRingRoad = isInsideRingRoad;
        this.houseNo = houseNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public boolean isInsideRingRoad() {
        return isInsideRingRoad;
    }

    public void setInsideRingRoad(boolean insideRingRoad) {
        isInsideRingRoad = insideRingRoad;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }
}
