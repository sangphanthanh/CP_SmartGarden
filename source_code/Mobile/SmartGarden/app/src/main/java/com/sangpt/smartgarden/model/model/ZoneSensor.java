package com.sangpt.smartgarden.model.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ManhNV on 7/13/2016.
 */
public class ZoneSensor {
    @SerializedName("humidity")
    private double humidity;
    @SerializedName("light")
    private double light;
    @SerializedName("ph")
    private double ph;
    @SerializedName("sensorId")
    private double sensorId;
    @SerializedName("soilMoisture")
    private double soilMoisture;
    @SerializedName("synctime")
    private String synctime;
    @SerializedName("temperature")
    private double temperature;
    @SerializedName("zoneId")
    private int zoneId;

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getSensorId() {
        return sensorId;
    }

    public void setSensorId(double sensorId) {
        this.sensorId = sensorId;
    }

    public double getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(double soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public String getSynctime() {
        return synctime;
    }

    public void setSynctime(String synctime) {
        this.synctime = synctime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

}
