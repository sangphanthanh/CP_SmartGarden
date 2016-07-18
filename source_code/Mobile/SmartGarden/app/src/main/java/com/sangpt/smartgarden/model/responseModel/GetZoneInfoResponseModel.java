package com.sangpt.smartgarden.model.responseModel;

import com.sangpt.smartgarden.model.model.ZoneIndex;

/**
 * Created by ManhNV on 7/13/2016.
 */
public class GetZoneInfoResponseModel extends ZoneIndex {
    public GetZoneInfoResponseModel(String userFertilizeDate, String userHighHumidity, String userHighLight, String userHighPh, String userHighSoilMoisture, String userHighTemperature, String userLowHumidity, String userLowLight, String userLowPh, String userLowSoilMoisture, String userLowTemperature, String userWeightFertilize, int userLibId, String userPlanName, String userTypePlan, String username) {
        super(userFertilizeDate, userHighHumidity, userHighLight, userHighPh, userHighSoilMoisture, userHighTemperature, userLowHumidity, userLowLight, userLowPh, userLowSoilMoisture, userLowTemperature, userWeightFertilize, userLibId, userPlanName, userTypePlan, username);
    }
}
