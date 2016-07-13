package com.sangpt.smartgarden.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.activity.ZoneInfoActivity;
import com.sangpt.smartgarden.model.model.ZoneIndex;
import com.sangpt.smartgarden.model.responseModel.GetZoneInfoSensorResponseModel;
import com.sangpt.smartgarden.services.RestService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ManhNV on 7/12/2016.
 */
public class SensorFragment extends Fragment {
    private int zoneId;
    private ZoneIndex zoneIndex;
    private RestService restService;
    private ActionBar toolbar;
    private ViewHolder viewHolder;
    private String zoneName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sensor, container, false);
        init(v);
        getSensor();
        return v;

    }

    private void getSensor() {
        restService.getiZoneService().getZoneSensor(zoneId, new Callback<GetZoneInfoSensorResponseModel>() {
            @Override
            public void success(GetZoneInfoSensorResponseModel responseModel, Response response) {
                if (responseModel != null) {
                    viewHolder.txtPh.setText(responseModel.getPh() + "");
                    viewHolder.txtLight.setText(responseModel.getLight() + "");
                    viewHolder.txtHumidity.setText(responseModel.getHumidity() + "");
                    viewHolder.txtSoil.setText(responseModel.getSoilMoisture() + "");
                    viewHolder.txtTemperature.setText(responseModel.getTemperature() + "");
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

    private void init(View v) {
        zoneId = getArguments().getInt("zoneId", -1);
        zoneName = getArguments().getString("zoneName", "");
        restService = new RestService();
        viewHolder = new ViewHolder();
        toolbar = ((ZoneInfoActivity) getActivity()).getSupportActionBar();
        viewHolder.txtPh = (EditText) v.findViewById(R.id.txt_sensor_ph);
        viewHolder.txtLight = (EditText) v.findViewById(R.id.txt_sensor_light);
        viewHolder.txtHumidity = (EditText) v.findViewById(R.id.txt_sensor_humidity);
        viewHolder.txtSoil = (EditText) v.findViewById(R.id.txt_sensor_soil);
        viewHolder.txtTemperature = (EditText) v.findViewById(R.id.txt_sensor_temperature);

    }

    private class ViewHolder {
        EditText txtPh;
        EditText txtLight;
        EditText txtHumidity;
        EditText txtSoil;
        EditText txtTemperature;
    }
}
