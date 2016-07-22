package com.sangpt.smartgarden.model.requestModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ManhNV on 7/18/2016.
 */
public class AddZoneRequestModel {
    @SerializedName("devideCode")
    private String devideCode;
    @SerializedName("location")
    private String location;
    @SerializedName("userLibId")
    private int userLibId;
    @SerializedName("zonename")
    private String zonename;
    @SerializedName("dateOfGrow")
    private String dateOfGrow;

    public AddZoneRequestModel(String devideCode, String location, int userLibId, String zonename) {
        this.devideCode = devideCode;
        this.location = location;
        this.userLibId = userLibId;
        this.zonename = zonename;
    }

    public AddZoneRequestModel(String devideCode, String location, int userLibId, String zonename, String dateOfGrow) {
        this.devideCode = devideCode;
        this.location = location;
        this.userLibId = userLibId;
        this.zonename = zonename;
        this.dateOfGrow = dateOfGrow;
    }

    public String getDevideCode() {
        return devideCode;
    }

    public void setDevideCode(String devideCode) {
        this.devideCode = devideCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserLibId() {
        return userLibId;
    }

    public void setUserLibId(int userLibId) {
        this.userLibId = userLibId;
    }

    public String getZonename() {
        return zonename;
    }

    public void setZonename(String zonename) {
        this.zonename = zonename;
    }

    public String getDateOfGrow() {
        return dateOfGrow;
    }

    public void setDateOfGrow(String dateOfGrow) {
        this.dateOfGrow = dateOfGrow;
    }
}
