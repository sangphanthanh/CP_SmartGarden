package com.sangpt.smartgarden.model.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ManhNV on 7/13/2016.
 */
public class ZoneActuator {
    @SerializedName("enddeviceId")
    private int endDeviceId;
    @SerializedName("statuscovered")
    private boolean statusCovered;
    @SerializedName("statusmisting")
    private boolean statusMisting;
    @SerializedName("statuspump")
    private boolean statusPump;
    @SerializedName("zoneId")
    private int zoneId;

    public int getEndDeviceId() {
        return endDeviceId;
    }

    public void setEndDeviceId(int endDeviceId) {
        this.endDeviceId = endDeviceId;
    }

    public boolean isStatusCovered() {
        return statusCovered;
    }

    public void setStatusCovered(boolean statusCovered) {
        this.statusCovered = statusCovered;
    }

    public boolean isStatusMisting() {
        return statusMisting;
    }

    public void setStatusMisting(boolean statusMisting) {
        this.statusMisting = statusMisting;
    }

    public boolean isStatusPump() {
        return statusPump;
    }

    public void setStatusPump(boolean statusPump) {
        this.statusPump = statusPump;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }
}
