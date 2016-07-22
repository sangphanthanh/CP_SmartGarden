package com.sangpt.smartgarden.services;


import com.sangpt.smartgarden.utils.DataUtils;

public class RestService {
    private static final String URL = DataUtils.URL;
//private static final String URL = "http://10.5.50.25:2315";
//    private static final String URL = "http://192.168.150.70:2315";
    private retrofit.RestAdapter restAdapter;
    private IAccountService iAccountService;
    private IZoneService iZoneService;

    public RestService() {
        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .build();
        iAccountService = restAdapter.create(IAccountService.class);
        iZoneService = restAdapter.create(IZoneService.class);
    }

    public IAccountService getAccountService() {
        return iAccountService;
    }

    public IZoneService getiZoneService() {
        return iZoneService;
    }
}
