package com.sangpt.smartgarden.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.activity.MainActivity;
import com.sangpt.smartgarden.activity.ZoneInfoActivity;
import com.sangpt.smartgarden.model.model.ZoneIndex;
import com.sangpt.smartgarden.model.responseModel.GetZoneInfoResponseModel;
import com.sangpt.smartgarden.services.RestService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ManhNV on 7/12/2016.
 */
public class IndexFragment extends Fragment {
    private int zoneId;
    private String zoneName;
    private ZoneIndex zoneIndex;
    private RestService restService;
    private ActionBar toolbar;
    private ViewHolder viewHolder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_index, container, false);

        init(v);
        getIndex();
        return v;

    }

    private void init(View v) {
        zoneId = getArguments().getInt("zoneId", -1);
        zoneName = getArguments().getString("zoneName", "");
        restService = new RestService();
        viewHolder = new ViewHolder();
        toolbar = ((ZoneInfoActivity) getActivity()).getSupportActionBar();
        viewHolder.txtPh = (EditText) v.findViewById(R.id.txt_index_ph);
        viewHolder.txtLight = (EditText) v.findViewById(R.id.txt_index_light);
        viewHolder.txtHumidity = (EditText) v.findViewById(R.id.txt_index_humidity);
        viewHolder.txtSoil = (EditText) v.findViewById(R.id.txt_index_soil);
        viewHolder.txtTemperature = (EditText) v.findViewById(R.id.txt_index_temperature);
        viewHolder.txtDateFertilize = (EditText) v.findViewById(R.id.txt_index_date_fertilizer);
        viewHolder.txtWeight = (EditText) v.findViewById(R.id.txt_index_weight_of_fertilizer);
        viewHolder.txtDevideCode = (EditText) v.findViewById(R.id.txt_index_device_code);
        viewHolder.txtLocation = (EditText) v.findViewById(R.id.txt_index_location);
        viewHolder.txtCreate = (EditText) v.findViewById(R.id.txt_index_create_date);
    }

    public void getIndex() {
        if (zoneId >= 0) {
            restService.getiZoneService().getZoneInfo(zoneId, new Callback<GetZoneInfoResponseModel>() {
                @Override
                public void success(GetZoneInfoResponseModel responseModel, Response response) {
                    if (responseModel != null) {
                        String s = zoneName + " - " + responseModel.getUserPlanName();
                        toolbar.setTitle(s);
                        viewHolder.txtPh.setText(responseModel.getUserLowPh() + " - " + responseModel.getUserHighPh());
                        viewHolder.txtLight.setText(responseModel.getUserLowLight() + " - " + responseModel.getUserHighLight());
                        viewHolder.txtHumidity.setText(responseModel.getUserLowHumidity() + " - " + responseModel.getUserHighHumidity());
                        viewHolder.txtSoil.setText(responseModel.getUserLowSoilMoisture() + " - " + responseModel.getUserHighSoilMoisture());
                        viewHolder.txtTemperature.setText(responseModel.getUserLowTemperature() + " - " + responseModel.getUserHighTemperature());
                        viewHolder.txtDateFertilize.setText(responseModel.getUserFertilizeDate());
                        viewHolder.txtWeight.setText(responseModel.getUserWeightFertilize() + "");
                        viewHolder.txtDevideCode.setText("");
                        viewHolder.txtLocation.setText("");
                        viewHolder.txtCreate.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Dữ liệu lỗi", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class ViewHolder {
        EditText txtPh;
        EditText txtLight;
        EditText txtHumidity;
        EditText txtSoil;
        EditText txtTemperature;
        EditText txtDateFertilize;
        EditText txtWeight;
        EditText txtDevideCode;
        EditText txtLocation;
        EditText txtCreate;
    }
}
