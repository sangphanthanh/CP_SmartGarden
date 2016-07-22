package com.sangpt.smartgarden.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.model.model.ZoneIndex;
import com.sangpt.smartgarden.model.requestModel.AddZoneRequestModel;
import com.sangpt.smartgarden.model.responseModel.ListLibResponseModel;
import com.sangpt.smartgarden.services.RestService;
import com.sangpt.smartgarden.utils.DataUtils;
import com.sangpt.smartgarden.utils.QuickSharePreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EnterZoneActivity extends AppCompatActivity {
    private ViewHolder viewHolder;
    private String username;
    private RestService restService;
    private List<String> libsString;
    private int userLib;

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
        userLib = -1;
        username = DataUtils.getINSTANCE(getApplicationContext()).getmPreferences().getString(QuickSharePreference.SHARE_USERNAME, "");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewHolder = new ViewHolder();
//        viewHolder.spinnerLib = (Spinner) findViewById(R.id.spinner_library);
        viewHolder.txtDeviceCode = (EditText) findViewById(R.id.txt_add_zone_device_code);
        viewHolder.txtLocation = (EditText) findViewById(R.id.txt_add_zone_location);
        viewHolder.txtZoneName = (EditText) findViewById(R.id.txt_add_zone_zone_name);
        viewHolder.btnLibrary = (Button) findViewById(R.id.btn_library);
//        createSpinner();
        showLibrary();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            final String zoneName = viewHolder.txtZoneName.getText().toString();
            String deviceCode = viewHolder.txtDeviceCode.getText().toString();
            String location = viewHolder.txtLocation.getText().toString();
            View focus = null;
            if (userLib < 0) {
                viewHolder.btnLibrary.setError("Please choose library!");
                if (focus == null) {
                    focus = viewHolder.btnLibrary;
                }
            }
            if (zoneName == null || zoneName.length() <= 0) {
                viewHolder.txtZoneName.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtZoneName;
                }
            }
            if (deviceCode == null || deviceCode.length() <= 0) {
                viewHolder.txtDeviceCode.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtDeviceCode;
                }
            }
            if (location == null || location.length() <= 0) {
                viewHolder.txtLocation.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtLocation;
                }
            }
            if (focus != null) {
                focus.requestFocus();
            } else {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
                Calendar c = Calendar.getInstance();
                java.util.Date date = c.getTime();
                AddZoneRequestModel requestModel = new AddZoneRequestModel(deviceCode, location, userLib, zoneName, dateFormatter.format(date));
                restService.getiZoneService().addZone(requestModel, new Callback<AddZoneRequestModel>() {
                    @Override
                    public void success(AddZoneRequestModel s, Response response) {
                        Toast.makeText(EnterZoneActivity.this, "Zone " + s.getZonename() + " is added", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(EnterZoneActivity.this, "Cant not saved", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void showLibrary() {
        viewHolder.btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                restService.getiZoneService().getLibrary(username, new Callback<ListLibResponseModel>() {
                    @Override
                    public void success(final ListLibResponseModel responseModel, Response response) {
                        if (responseModel != null) {
                            libsString = new ArrayList<String>();
                            for (ZoneIndex item : responseModel.getLibs()) {
                                libsString.add(item.getUserPlanName());
                            }
                            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter(EnterZoneActivity.this, R.layout.item_spinner, libsString);
                            arrayAdapter.setDropDownViewResource(R.layout.item_spinner);
                            new AlertDialog.Builder(EnterZoneActivity.this)
                                    .setTitle("Please choose a library")
                                    .setPositiveButton("Add new library", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(EnterZoneActivity.this, AddLibActivity.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.right_in, R.anim.left_out);
//                                    final EditText view = new EditText(main);
//                                    new AlertDialog.Builder(main)
//                                            .setTitle("Please enter a name")
//                                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    adapter.add(view.getText().toString());
//                                                    selectBtn.setText(view.getText().toString());
//                                                }
//                                            })
//                                            .setNegativeButton("cancel", null)
//                                            .setView(view)
//                                            .show();
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            viewHolder.btnLibrary.setText(arrayAdapter.getItem(which));
                                            userLib = responseModel.getLibs().get(which).getUserLibId();
                                            dialog.dismiss();
                                        }
                                    }).create().show();

                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });

    }

    private void createSpinner() {
        restService.getiZoneService().getLibrary(username, new Callback<ListLibResponseModel>() {
            @Override
            public void success(ListLibResponseModel responseModel, Response response) {
                if (responseModel != null) {
                    libsString = new ArrayList<String>();
                    for (ZoneIndex item : responseModel.getLibs()) {
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
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    private class ViewHolder {
        Spinner spinnerLib;
        EditText txtDeviceCode;
        EditText txtLocation;
        Button btnLibrary;
        public EditText txtZoneName;
    }
}
