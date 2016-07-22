package com.sangpt.smartgarden.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.activity.ZoneInfoActivity;
import com.sangpt.smartgarden.model.model.ZoneActuator;
import com.sangpt.smartgarden.model.model.ZoneIndex;
import com.sangpt.smartgarden.model.responseModel.GetZoneInfoActuatorResponseModel;
import com.sangpt.smartgarden.services.RestService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ManhNV on 7/12/2016.
 */
public class ActuatorFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private int zoneId;
    private ZoneIndex zoneIndex;
    private RestService restService;
    private ActionBar toolbar;
    private ViewHolder viewHolder;
    private ZoneActuator zoneActuator;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_actuator,container,false);
        initView(v);
        event();
        return v;

    }

    private void event() {
        restService.getiZoneService().getZoneActuator(zoneId, new Callback<GetZoneInfoActuatorResponseModel>() {
            @Override
            public void success(GetZoneInfoActuatorResponseModel responseModel, Response response) {
                if (responseModel!=null){
                    zoneActuator = responseModel;
                   viewHolder.scCovered.setChecked(responseModel.isStatusCovered());
                    viewHolder.scMisting.setChecked(responseModel.isStatusMisting());
                    viewHolder.scPump.setChecked(responseModel.isStatusPump());
                    viewHolder.scFertilize.setChecked(responseModel.isStatusFertilize());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void initView(View v) {
        zoneId = getArguments().getInt("zoneId", -1);
        restService = new RestService();
        viewHolder = new ViewHolder();
        toolbar = ((ZoneInfoActivity) getActivity()).getSupportActionBar();
        viewHolder.scCovered = (SwitchCompat) v.findViewById(R.id.sc_zone_status_covered);
        viewHolder.scMisting = (SwitchCompat) v.findViewById(R.id.sc_zone_status_misting);
        viewHolder.scPump = (SwitchCompat) v.findViewById(R.id.sc_zone_status_pump);
        viewHolder.scFertilize = (SwitchCompat) v.findViewById(R.id.sc_zone_status_fertilize);
        viewHolder.cbAutomatically = (CheckBox) v.findViewById(R.id.cb_automatically);
        viewHolder.scCovered.setChecked(false);
        viewHolder.scMisting.setChecked(false);
        viewHolder.scPump.setChecked(false);
        viewHolder.scFertilize.setChecked(false);
        viewHolder.cbAutomatically.setChecked(false);
        viewHolder.cbAutomatically.setOnCheckedChangeListener(this);
        viewHolder.scFertilize.setOnCheckedChangeListener(this);
        viewHolder.scPump.setOnCheckedChangeListener(this);
        viewHolder.scMisting.setOnCheckedChangeListener(this);
        viewHolder.scCovered.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ZoneActuator actuator = new ZoneActuator();
        actuator.setEndDeviceId(zoneActuator.getEndDeviceId());
        actuator.setZoneId(zoneActuator.getZoneId());
        actuator.setStatusCovered(viewHolder.scCovered.isChecked());
        actuator.setStatusMisting(viewHolder.scMisting.isChecked());
        actuator.setStatusPump(viewHolder.scPump.isChecked());
        actuator.setStatusFertilize(viewHolder.scFertilize.isChecked());
        actuator.setAutomatically(viewHolder.cbAutomatically.isChecked());
        restService.getiZoneService().updateActuator(actuator, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Toast.makeText(getActivity(), "Thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), error.getResponse().getReason(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class ViewHolder {
        SwitchCompat scCovered;
        SwitchCompat scMisting;
        SwitchCompat scPump;
        SwitchCompat scFertilize;
        CheckBox cbAutomatically;
    }
}
