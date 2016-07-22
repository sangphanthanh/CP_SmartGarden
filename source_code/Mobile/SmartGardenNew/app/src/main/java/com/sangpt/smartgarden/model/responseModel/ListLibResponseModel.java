package com.sangpt.smartgarden.model.responseModel;

import com.google.gson.annotations.SerializedName;
import com.sangpt.smartgarden.model.model.ZoneIndex;

import java.util.List;

/**
 * Created by ManhNV on 7/14/2016.
 */
public class ListLibResponseModel {
    @SerializedName("userlibrary")
    private List<ZoneIndex> libs;

    public List<ZoneIndex> getLibs() {
        return libs;
    }

    public void setLibs(List<ZoneIndex> libs) {
        this.libs = libs;
    }
}
