package com.sangpt.smartgarden.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.activity.EnterZoneActivity;
import com.sangpt.smartgarden.activity.ZoneInfoActivity;
import com.sangpt.smartgarden.model.model.ZoneIndex;
import com.sangpt.smartgarden.model.model.ZoneSensor;
import com.sangpt.smartgarden.model.responseModel.GetListZoneSensorResponseModel;
import com.sangpt.smartgarden.model.responseModel.GetZoneInfoSensorResponseModel;
import com.sangpt.smartgarden.model.responseModel.ListLibResponseModel;
import com.sangpt.smartgarden.services.RestService;

import java.util.ArrayList;
import java.util.List;

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
    private List<ZoneSensor> sensorList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sensor, container, false);
        init(v);
        getSensor();
        return v;

    }

    private void getSensor() {
       restService.getiZoneService().getListZoneSensor(zoneId, new Callback<GetListZoneSensorResponseModel>() {
           @Override
           public void success(GetListZoneSensorResponseModel responseModel, Response response) {
               if (responseModel!=null && responseModel.getSensorList()!=null){
                   sensorList = responseModel.getSensorList();
                   createSpinner();
               }
           }

           @Override
           public void failure(RetrofitError error) {

           }
       });
    }

    private void init(View v) {
        zoneId = getArguments().getInt("zoneId", -1);
        zoneName = getArguments().getString("zoneName", "");
        restService = new RestService();
        viewHolder = new ViewHolder();
        toolbar = ((ZoneInfoActivity) getActivity()).getSupportActionBar();
        viewHolder.txtPh = (TextView) v.findViewById(R.id.txt_sensor_ph);
        viewHolder.txtLight = (TextView) v.findViewById(R.id.txt_sensor_light);
        viewHolder.txtHumidity = (TextView) v.findViewById(R.id.txt_sensor_humidity);
        viewHolder.txtSoil = (TextView) v.findViewById(R.id.txt_sensor_soil);
        viewHolder.txtTemperature = (TextView) v.findViewById(R.id.txt_sensor_temperature);
//        viewHolder.txtSyncTime = (TextView) v.findViewById(R.id.txt_sensor_synctime);
        viewHolder.spinner = (Spinner) v.findViewById(R.id.spin_sync_date);
    }

    private class ViewHolder {
        TextView txtPh;
        TextView txtLight;
        TextView txtHumidity;
        TextView txtSoil;
        TextView txtTemperature;
        TextView txtSyncTime;
        Spinner spinner;
    }

    private void createSpinner() {
        List<String> synctimes = new ArrayList<String>();
        for (ZoneSensor item : sensorList) {
            synctimes.add(item.getSynctime());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getActivity(), R.layout.item_spinner, synctimes);
        arrayAdapter.setDropDownViewResource(R.layout.item_spinner);
        viewHolder.spinner.setAdapter(arrayAdapter);
        viewHolder.spinner.setSelection(0);
        viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewHolder.txtPh.setText(sensorList.get(position).getPh()+"");
                viewHolder.txtLight.setText(sensorList.get(position).getLight() + "");
                viewHolder.txtHumidity.setText(sensorList.get(position).getHumidity() + "");
                viewHolder.txtSoil.setText(sensorList.get(position).getSoilMoisture() + "");
                viewHolder.txtTemperature.setText(sensorList.get(position).getTemperature() + "");
//                viewHolder.txtSyncTime.setText(sensorList.get(position).getSynctime());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
