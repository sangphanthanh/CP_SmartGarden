package com.sangpt.smartgarden.model.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ManhNV on 7/13/2016.
 */
public class ZoneIndex {
    @SerializedName("userFertilizeDate")
    private String userFertilizeDate;
    @SerializedName("userHighHumidity")
    private double userHighHumidity;
    @SerializedName("userHighLight")
    private double userHighLight;
    @SerializedName("userHighPh")
    private double userHighPh;
    @SerializedName("userHighSoilMoisture")
    private double userHighSoilMoisture;
    @SerializedName("userHighTemperature")
    private double userHighTemperature;
    @SerializedName("userLibId")
    private int userLibId;
    @SerializedName("userLowHumidity")
    private double userLowHumidity;
    @SerializedName("userLowLight")
    private double userLowLight;
    @SerializedName("userLowPh")
    private double userLowPh;
    @SerializedName("userLowSoilMoisture")
    private double userLowSoilMoisture;
    @SerializedName("userLowTemperature")
    private double userLowTemperature;
    @SerializedName("userPlanName")
    private String userPlanName;
    @SerializedName("userTypePlan")
    private String userTypePlan;
    @SerializedName("userWeightFertilize")
    private double userWeightFertilize;
    @SerializedName("username")
    private String username;

    public String getUserFertilizeDate() {
        return userFertilizeDate;
    }

    public void setUserFertilizeDate(String userFertilizeDate) {
        this.userFertilizeDate = userFertilizeDate;
    }

    public double getUserHighHumidity() {
        return userHighHumidity;
    }

    public void setUserHighHumidity(double userHighHumidity) {
        this.userHighHumidity = userHighHumidity;
    }

    public double getUserHighLight() {
        return userHighLight;
    }

    public void setUserHighLight(double userHighLight) {
        this.userHighLight = userHighLight;
    }

    public double getUserHighPh() {
        return userHighPh;
    }

    public void setUserHighPh(double userHighPh) {
        this.userHighPh = userHighPh;
    }

    public double getUserHighSoilMoisture() {
        return userHighSoilMoisture;
    }

    public void setUserHighSoilMoisture(double userHighSoilMoisture) {
        this.userHighSoilMoisture = userHighSoilMoisture;
    }

    public double getUserHighTemperature() {
        return userHighTemperature;
    }

    public void setUserHighTemperature(double userHighTemperature) {
        this.userHighTemperature = userHighTemperature;
    }

    public int getUserLibId() {
        return userLibId;
    }

    public void setUserLibId(int userLibId) {
        this.userLibId = userLibId;
    }

    public double getUserLowHumidity() {
        return userLowHumidity;
    }

    public void setUserLowHumidity(double userLowHumidity) {
        this.userLowHumidity = userLowHumidity;
    }

    public double getUserLowLight() {
        return userLowLight;
    }

    public void setUserLowLight(double userLowLight) {
        this.userLowLight = userLowLight;
    }

    public double getUserLowPh() {
        return userLowPh;
    }

    public void setUserLowPh(double userLowPh) {
        this.userLowPh = userLowPh;
    }

    public double getUserLowSoilMoisture() {
        return userLowSoilMoisture;
    }

    public void setUserLowSoilMoisture(double userLowSoilMoisture) {
        this.userLowSoilMoisture = userLowSoilMoisture;
    }

    public double getUserLowTemperature() {
        return userLowTemperature;
    }

    public void setUserLowTemperature(double userLowTemperature) {
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

    public double getUserWeightFertilize() {
        return userWeightFertilize;
    }

    public void setUserWeightFertilize(double userWeightFertilize) {
        this.userWeightFertilize = userWeightFertilize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
