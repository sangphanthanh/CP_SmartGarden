package com.sangpt.smartgarden.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.model.model.Library;
import com.sangpt.smartgarden.services.RestService;
import com.sangpt.smartgarden.utils.DataUtils;
import com.sangpt.smartgarden.utils.QuickSharePreference;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AddLibActivity extends AppCompatActivity {

    private RestService restService;
    private String username;
    private ViewHolder viewHolder;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lib);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Add new Library");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        restService = new RestService();
        username = DataUtils.getINSTANCE(getApplicationContext()).getmPreferences().getString(QuickSharePreference.SHARE_USERNAME, "");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = this;
        viewHolder = new ViewHolder();
        viewHolder.txtPhHigh = (EditText) findViewById(R.id.txt_index_ph_high);
        viewHolder.txtLightHigh = (EditText) findViewById(R.id.txt_index_light_high);
        viewHolder.txtHumidityHigh = (EditText) findViewById(R.id.txt_index_humidity_high);
        viewHolder.txtSoilHigh = (EditText) findViewById(R.id.txt_index_soil_high);
        viewHolder.txtTemperatureHigh = (EditText) findViewById(R.id.txt_index_temperature_high);
        viewHolder.txtPhLow = (EditText) findViewById(R.id.txt_index_ph_low);
        viewHolder.txtLightLow = (EditText) findViewById(R.id.txt_index_light_low);
        viewHolder.txtHumidityLow = (EditText) findViewById(R.id.txt_index_humidity_low);
        viewHolder.txtSoilLow = (EditText) findViewById(R.id.txt_index_soil_low);
        viewHolder.txtTemperatureLow = (EditText) findViewById(R.id.txt_index_temperature_low);
        viewHolder.txtDateFertilize = (EditText) findViewById(R.id.txt_index_date_fertilizer);
        viewHolder.txtWeight = (EditText) findViewById(R.id.txt_index_weight_of_fertilizer);
        viewHolder.txtPlant = (EditText) findViewById(R.id.txt_add_lib_plant);
        viewHolder.txtTypePlant = (EditText) findViewById(R.id.txt_add_lib_type_plant);
        setDobField(viewHolder.txtDateFertilize);
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
            String phLow = viewHolder.txtPhLow.getText().toString();
            String phHigh = viewHolder.txtPhHigh.getText().toString();
            String lightLow = viewHolder.txtLightLow.getText().toString();
            String lightHigh = viewHolder.txtLightHigh.getText().toString();
            String humidityLow = viewHolder.txtHumidityLow.getText().toString();
            String humidityHigh = viewHolder.txtHumidityHigh.getText().toString();
            String soilLow = viewHolder.txtSoilLow.getText().toString();
            String soilHigh = viewHolder.txtSoilHigh.getText().toString();
            String tempLow = viewHolder.txtTemperatureLow.getText().toString();
            String tempHigh = viewHolder.txtTemperatureHigh.getText().toString();
            String dateFertilizer = viewHolder.txtDateFertilize.getText().toString();
            String weightOfFertilizer = viewHolder.txtWeight.getText().toString();
            String plant = viewHolder.txtPlant.getText().toString();
            String typePlant = viewHolder.txtTypePlant.getText().toString();
            View focus = null;
            if (plant == null || plant.length() <= 0) {
                viewHolder.txtPlant.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtPlant;
                }
            }
            if (typePlant == null || typePlant.length() <= 0) {
                viewHolder.txtTypePlant.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtTypePlant;
                }
            }
            if (phLow == null || phLow.length() <= 0) {
                viewHolder.txtPhLow.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtPhLow;
                }
            }
            if (phHigh == null || phHigh.length() <= 0) {
                viewHolder.txtPhHigh.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtPhHigh;
                }
            }
            if (lightLow == null || lightLow.length() <= 0) {
                viewHolder.txtLightLow.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtLightLow;
                }
            }
            if (lightHigh == null || lightHigh.length() <= 0) {
                viewHolder.txtLightHigh.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtLightHigh;
                }
            }
            if (humidityLow == null || humidityLow.length() <= 0) {
                viewHolder.txtHumidityLow.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtHumidityLow;
                }
            }
            if (humidityHigh == null || humidityHigh.length() <= 0) {
                viewHolder.txtHumidityHigh.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtHumidityHigh;
                }
            }
            if (soilLow == null || soilLow.length() <= 0) {
                viewHolder.txtSoilLow.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtSoilLow;
                }
            }
            if (soilHigh == null || soilHigh.length() <= 0) {
                viewHolder.txtSoilHigh.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtSoilHigh;
                }
            }
            if (tempLow == null || tempLow.length() <= 0) {
                viewHolder.txtTemperatureLow.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtTemperatureLow;
                }
            }
            if (tempHigh == null || tempHigh.length() <= 0) {
                viewHolder.txtTemperatureHigh.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtTemperatureHigh;
                }
            }

            if (dateFertilizer == null || dateFertilizer.length() <= 0) {
                viewHolder.txtDateFertilize.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtDateFertilize;
                }
            }
            if (weightOfFertilizer == null || weightOfFertilizer.length() <= 0) {
                viewHolder.txtWeight.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtWeight;
                }
            }


            if (focus != null) {
                focus.requestFocus();
            } else {
                Library library = new Library(dateFertilizer,
                        humidityHigh,
                        lightHigh,
                        phHigh,
                        soilHigh,
                        tempHigh,
                        humidityLow,
                        lightLow,
                        phLow,
                        soilLow,
                        tempLow,
                        weightOfFertilizer,
                        0,
                        plant, typePlant, username);
                restService.getiZoneService().addLib(library, new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        Toast.makeText(AddLibActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(AddLibActivity.this, "Can't save", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setDobField(final EditText editText) {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        final SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss", Locale.US);
        final Calendar newCalendar = Calendar.getInstance();
        final String[] dateTime = {"", ""};
        final TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Time time = new Time(hourOfDay, minute, 0);
                dateTime[1] = timeFormatter.format(time);
                editText.setText(dateTime[0] + " " + dateTime[1]);
            }
        }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), true);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
//                editText.setText(dateFormatter.format(newDate.getTime()));
                dateTime[0] = dateFormatter.format(newDate.getTime());
                timePickerDialog.show();

            }

        }, (newCalendar.get(Calendar.YEAR)), (newCalendar.get(Calendar.MONTH)), (newCalendar.get(Calendar.DATE)));
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    datePickerDialog.show();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    private class ViewHolder {
        EditText txtPhHigh;
        EditText txtLightHigh;
        EditText txtHumidityHigh;
        EditText txtSoilHigh;
        EditText txtTemperatureHigh;
        EditText txtPhLow;
        EditText txtLightLow;
        EditText txtHumidityLow;
        EditText txtSoilLow;
        EditText txtTemperatureLow;
        EditText txtDateFertilize;
        EditText txtWeight;
        EditText txtPlant;
        EditText txtTypePlant;
    }

}
