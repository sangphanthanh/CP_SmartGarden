package com.sangpt.smartgarden.model.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ManhNV on 7/18/2016.
 */
public class Library {

    @SerializedName("userFertilizeDate")
    private String userFertilizeDate;
    @SerializedName("userHighHumidity")
    private String userHighHumidity;
    @SerializedName("userHighLight")
    private String userHighLight;
    @SerializedName("userHighPh")
    private String userHighPh;
    @SerializedName("userHighSoilMoisture")
    private String userHighSoilMoisture;
    @SerializedName("userHighTemperature")
    private String userHighTemperature;
    @SerializedName("userLowHumidity")
    private String userLowHumidity;
    @SerializedName("userLowLight")
    private String userLowLight;
    @SerializedName("userLowPh")
    private String userLowPh;
    @SerializedName("userLowSoilMoisture")
    private String userLowSoilMoisture;
    @SerializedName("userLowTemperature")
    private String userLowTemperature;
    @SerializedName("userWeightFertilize")
    private String userWeightFertilize;
    @SerializedName("userLibId")
    private int userLibId;
    @SerializedName("userPlanName")
    private String userPlanName;
    @SerializedName("userTypePlan")
    private String userTypePlan;
    @SerializedName("username")
    private String username;

    public Library(String userFertilizeDate, String userHighHumidity, String userHighLight, String userHighPh, String userHighSoilMoisture, String userHighTemperature, String userLowHumidity, String userLowLight, String userLowPh, String userLowSoilMoisture, String userLowTemperature, String userWeightFertilize, int userLibId, String userPlanName, String userTypePlan, String username) {
        this.userFertilizeDate = userFertilizeDate;
        this.userHighHumidity = userHighHumidity;
        this.userHighLight = userHighLight;
        this.userHighPh = userHighPh;
        this.userHighSoilMoisture = userHighSoilMoisture;
        this.userHighTemperature = userHighTemperature;
        this.userLowHumidity = userLowHumidity;
        this.userLowLight = userLowLight;
        this.userLowPh = userLowPh;
        this.userLowSoilMoisture = userLowSoilMoisture;
        this.userLowTemperature = userLowTemperature;
        this.userWeightFertilize = userWeightFertilize;
        this.userLibId = userLibId;
        this.userPlanName = userPlanName;
        this.userTypePlan = userTypePlan;
        this.username = username;
    }

    public String getUserFertilizeDate() {
        return userFertilizeDate;
    }

    public void setUserFertilizeDate(String userFertilizeDate) {
        this.userFertilizeDate = userFertilizeDate;
    }

    public String getUserHighHumidity() {
        return userHighHumidity;
    }

    public void setUserHighHumidity(String userHighHumidity) {
        this.userHighHumidity = userHighHumidity;
    }

    public String getUserHighLight() {
        return userHighLight;
    }

    public void setUserHighLight(String userHighLight) {
        this.userHighLight = userHighLight;
    }

    public String getUserHighPh() {
        return userHighPh;
    }

    public void setUserHighPh(String userHighPh) {
        this.userHighPh = userHighPh;
    }

    public String getUserHighSoilMoisture() {
        return userHighSoilMoisture;
    }

    public void setUserHighSoilMoisture(String userHighSoilMoisture) {
        this.userHighSoilMoisture = userHighSoilMoisture;
    }

    public String getUserHighTemperature() {
        return userHighTemperature;
    }

    public void setUserHighTemperature(String userHighTemperature) {
        this.userHighTemperature = userHighTemperature;
    }

    public int getUserLibId() {
        return userLibId;
    }

    public void setUserLibId(int userLibId) {
        this.userLibId = userLibId;
    }

    public String getUserLowHumidity() {
        return userLowHumidity;
    }

    public void setUserLowHumidity(String userLowHumidity) {
        this.userLowHumidity = userLowHumidity;
    }

    public String getUserLowLight() {
        return userLowLight;
    }

    public void setUserLowLight(String userLowLight) {
        this.userLowLight = userLowLight;
    }

    public String getUserLowPh() {
        return userLowPh;
    }

    public void setUserLowPh(String userLowPh) {
        this.userLowPh = userLowPh;
    }

    public String getUserLowSoilMoisture() {
        return userLowSoilMoisture;
    }

    public void setUserLowSoilMoisture(String userLowSoilMoisture) {
        this.userLowSoilMoisture = userLowSoilMoisture;
    }

    public String getUserLowTemperature() {
        return userLowTemperature;
    }

    public void setUserLowTemperature(String userLowTemperature) {
        this.userLowTemperature = userLowTemperature;
    }

    public String getUserPlanName() {
        return userPlanName;
    }

    public void setUserPlanName(String userPlanName) {
        this.userPlanName = userPlanName;
    }

    public String getUserTypePlan() {
        return userTypePlan;
    }

    public void setUserTypePlan(String userTypePlan) {
        this.userTypePlan = userTypePlan;
    }

    public String getUserWeightFertilize() {
        return userWeightFertilize;
    }

    public void setUserWeightFertilize(String userWeightFertilize) {
        this.userWeightFertilize = userWeightFertilize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
