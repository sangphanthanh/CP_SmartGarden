package com.sangpt.smartgarden.model.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ManhNV on 7/13/2016.
 */
public class ZoneIndex  extends Library{


    @SerializedName("location")
    private String location;
    @SerializedName("devideCode")
    private String devideCode;
    @SerializedName("dateOfGrow")
    private String dateOfGrow;

    public ZoneIndex(String userFertilizeDate, String userHighHumidity, String userHighLight, String userHighPh, String userHighSoilMoisture, String userHighTemperature, String userLowHumidity, String userLowLight, String userLowPh, String userLowSoilMoisture, String userLowTemperature, String userWeightFertilize, int userLibId, String userPlanName, String userTypePlan, String username) {
        super(userFertilizeDate, userHighHumidity, userHighLight, userHighPh, userHighSoilMoisture, userHighTemperature, userLowHumidity, userLowLight, userLowPh, userLowSoilMoisture, userLowTemperature, userWeightFertilize, userLibId, userPlanName, userTypePlan, username);
    }


    public ZoneIndex(String userFertilizeDate, String userHighHumidity, String userHighLight, String userHighPh, String userHighSoilMoisture, String userHighTemperature, String userLowHumidity, String userLowLight, String userLowPh, String userLowSoilMoisture, String userLowTemperature, String userWeightFertilize, int userLibId, String userPlanName, String userTypePlan, String username, String location, String devideCode, String dateOfGrow) {
        super(userFertilizeDate, userHighHumidity, userHighLight, userHighPh, userHighSoilMoisture, userHighTemperature, userLowHumidity, userLowLight, userLowPh, userLowSoilMoisture, userLowTemperature, userWeightFertilize, userLibId, userPlanName, userTypePlan, username);
        this.location = location;
        this.devideCode = devideCode;
        this.dateOfGrow = dateOfGrow;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDevideCode() {
        return devideCode;
    }

    public void setDevideCode(String devideCode) {
        this.devideCode = devideCode;
    }

    public String getDateOfGrow() {
        return dateOfGrow;
    }

    public void setDateOfGrow(String dateOfGrow) {
        this.dateOfGrow = dateOfGrow;
    }
}
