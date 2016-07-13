package com.sangpt.smartgarden.services;

import com.sangpt.smartgarden.model.responseModel.GetZoneInfoResponseModel;
import com.sangpt.smartgarden.model.responseModel.GetZoneInfoSensorResponseModel;
import com.sangpt.smartgarden.model.responseModel.GetZonesResponseModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ManhNV on 7/12/2016.
 */
public interface IZoneService {
    @GET("/rest/zone/{username}/findzonebyuserlidid")
    void getZones(@Path("username") String username, Callback<GetZonesResponseModel> callback);

    @GET("/rest/userlibrary/{zoneId}/infoplan")
    void getZoneInfo(@Path("zoneId") int zoneId,
                     Callback<GetZoneInfoResponseModel> callback);

    @GET("/rest/sensor/{zoneId}/sensorinfolistjson")
    void getZoneSensor(@Path("zoneId") int zoneId,
                     Callback<GetZoneInfoSensorResponseModel> callback);
}
