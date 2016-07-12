package com.sangpt.smartgarden.services;

import com.sangpt.smartgarden.model.responseModel.LoginResponseModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ManhNV on 7/11/2016.
 */
public interface IAccountService {

    @GET("/rest/account/{username}/{password}/loginjson")
    void checkLogin(@Path("username") String username,
                    @Path("password") String password,
                    Callback<LoginResponseModel> callback);
}
