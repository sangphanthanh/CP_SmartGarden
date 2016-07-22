package com.sangpt.smartgarden.model.responseModel;

import com.google.gson.annotations.SerializedName;
import com.sangpt.smartgarden.model.model.Zone;

import java.util.List;

/**
 * Created by ManhNV on 7/12/2016.
 */
public class GetZonesResponseModel {
    @SerializedName("zone")
    private List<Zone> zones;
    @SerializedName("zones")
    private Zone zone;

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
