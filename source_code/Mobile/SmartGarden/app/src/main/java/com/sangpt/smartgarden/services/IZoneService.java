package com.sangpt.smartgarden.services;

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
}
