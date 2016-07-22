package com.sangpt.smartgarden.model.responseModel;

import com.google.gson.annotations.SerializedName;
import com.sangpt.smartgarden.model.model.ZoneSensor;

import java.util.List;

/**
 * Created by ManhNV on 7/19/2016.
 */
public class GetListZoneSensorResponseModel {
    @SerializedName("sensorinfo")
    private List<ZoneSensor> sensorList;

    public List<ZoneSensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(List<ZoneSensor> sensorList) {
        this.sensorList = sensorList;
    }
}
