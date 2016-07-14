package com.sangpt.smartgarden.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.model.model.ZoneIndex;
import com.sangpt.smartgarden.model.responseModel.ListLibResponseModel;
import com.sangpt.smartgarden.services.RestService;
import com.sangpt.smartgarden.utils.DataUtils;
import com.sangpt.smartgarden.utils.QuickSharePreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EnterZoneActivity extends AppCompatActivity {
    private ViewHolder viewHolder;
    private String username;
    private RestService restService;
    private List<String> libsString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_zone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Enter Zone");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        restService = new RestService();
        username = DataUtils.getINSTANCE(getApplicationContext()).getmPreferences().getString(QuickSharePreference.SHARE_USERNAME,"");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewHolder = new ViewHolder();
        viewHolder.spinnerLib = (Spinner) findViewById(R.id.spinner_library);
        viewHolder.txtDeviceCode = (EditText) findViewById(R.id.txt_add_zone_device_code);
        viewHolder.txtLocation = (EditText) findViewById(R.id.txt_add_zone_location);
        viewHolder.txtCreateDate = (EditText) findViewById(R.id.txt_add_zone_create_date);
        createSpinner();
    }

    private void createSpinner() {
        restService.getiZoneService().getLibrary(username, new Callback<ListLibResponseModel>() {
            @Override
            public void success(ListLibResponseModel responseModel, Response response) {
                if (responseModel!=null){
                    libsString = new ArrayList<String>();
                    for (ZoneIndex item:responseModel.getLibs()){
                        libsString.add(item.getUserPlanName());
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter(EnterZoneActivity.this, R.layout.item_spinner, libsString);
                    arrayAdapter.setDropDownViewResource(R.layout.item_spinner);
                    viewHolder.spinnerLib.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }

    private class ViewHolder{
        Spinner spinnerLib;
        EditText txtDeviceCode;
        EditText txtLocation;
        EditText txtCreateDate;
    }
}
