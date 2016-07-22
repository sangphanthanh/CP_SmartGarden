package com.sangpt.smartgarden.model.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ManhNV on 7/12/2016.
 */
public class Zone {

    @SerializedName("dateOfGrow")
    private String dateOfGrow;
    @SerializedName("devideCode")
    private String devideCode;
    @SerializedName("location")
    private String location;
    @SerializedName("userLibId")
    private String userLibId;
    @SerializedName("zoneId")
    private int zoneId;
    @SerializedName("zonename")
    private String zoneName;

    public String getDateOfGrow() {
        return dateOfGrow;
    }

    public void setDateOfGrow(String dateOfGrow) {
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

    public String getUserLibId() {
        return userLibId;
    }

    public void setUserLibId(String userLibId) {
        this.userLibId = userLibId;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
}
