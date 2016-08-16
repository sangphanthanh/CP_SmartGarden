package com.sangpt.smartgarden.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
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
public class ActuatorFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener

{
    private int zoneId;
    private ZoneIndex zoneIndex;
    private RestService restService;
    private ActionBar toolbar;
    private ViewHolder viewHolder;
    private ZoneActuator zoneActuator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_actuator, container, false);
        initView(v);
        event();
        return v;

    }

    private void event() {
        restService.getiZoneService().getZoneActuator(zoneId, new Callback<GetZoneInfoActuatorResponseModel>() {
            @Override
            public void success(GetZoneInfoActuatorResponseModel responseModel, Response response) {
                if (responseModel != null) {
                    zoneActuator = responseModel;
                    viewHolder.scCovered.setChecked(responseModel.isStatusCovered());
                    viewHolder.scMisting.setChecked(responseModel.isStatusMisting());
                    viewHolder.scPump.setChecked(responseModel.isStatusPump());
                    viewHolder.scFertilize.setChecked(responseModel.isStatusFertilize());
                    viewHolder.cbAutomatically.setChecked(responseModel.isAutomatically());
                    viewHolder.scLamp.setChecked(responseModel.isStatusLamp());
                    if (responseModel.isStatusFertilize()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Warning")
                                .setMessage("Need to be Fertilize")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();
                    }

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
        viewHolder.scLamp = (SwitchCompat) v.findViewById(R.id.sc_zone_status_lamp);
        viewHolder.scCovered.setChecked(false);
        viewHolder.scMisting.setChecked(false);
        viewHolder.scPump.setChecked(false);
        viewHolder.scFertilize.setChecked(false);
        viewHolder.scLamp.setChecked(false);
        viewHolder.cbAutomatically.setChecked(false);
        viewHolder.cbAutomatically.setOnClickListener(this);
        viewHolder.scFertilize.setOnClickListener(this);
        viewHolder.scPump.setOnClickListener(this);
        viewHolder.scMisting.setOnClickListener(this);
        viewHolder.scCovered.setOnClickListener(this);
        viewHolder.scLamp.setOnClickListener(this);
        viewHolder.cbAutomatically.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.cb_automatically:
                if (viewHolder.cbAutomatically.isChecked()) {
                    viewHolder.scCovered.setEnabled(false);
                    viewHolder.scMisting.setEnabled(false);
                    viewHolder.scPump.setEnabled(false);
                    viewHolder.scFertilize.setEnabled(false);
                } else {
                    viewHolder.scCovered.setEnabled(true);
                    viewHolder.scMisting.setEnabled(true);
                    viewHolder.scPump.setEnabled(true);
                    viewHolder.scFertilize.setEnabled(true);
                }
        }
        ZoneActuator actuator = new ZoneActuator();
        actuator.setEndDeviceId(zoneActuator.getEndDeviceId());
        actuator.setZoneId(zoneActuator.getZoneId());
        actuator.setStatusCovered(viewHolder.scCovered.isChecked());
        actuator.setStatusMisting(viewHolder.scMisting.isChecked());
        actuator.setStatusPump(viewHolder.scPump.isChecked());
        actuator.setStatusLamp(viewHolder.scLamp.isChecked());
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        if (id == R.id.cb_automatically) {
            if (isChecked) {
                viewHolder.scCovered.setEnabled(false);
                viewHolder.scMisting.setEnabled(false);
                viewHolder.scPump.setEnabled(false);
                viewHolder.scFertilize.setEnabled(false);
            } else {
                viewHolder.scCovered.setEnabled(true);
                viewHolder.scMisting.setEnabled(true);
                viewHolder.scPump.setEnabled(true);
                viewHolder.scFertilize.setEnabled(true);
            }
        }
    }

    private class ViewHolder {
        SwitchCompat scCovered;
        SwitchCompat scMisting;
        SwitchCompat scPump;
        SwitchCompat scFertilize;
        CheckBox cbAutomatically;
        SwitchCompat scLamp;
    }
}
