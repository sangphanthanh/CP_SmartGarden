package com.sangpt.smartgarden.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.activity.EnterZoneActivity;
import com.sangpt.smartgarden.activity.MainActivity;
import com.sangpt.smartgarden.activity.ZoneInfoActivity;
import com.sangpt.smartgarden.model.model.ZoneIndex;
import com.sangpt.smartgarden.model.responseModel.GetZoneInfoResponseModel;
import com.sangpt.smartgarden.services.RestService;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_index, container, false);
        mContext = getActivity();
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
        viewHolder.txtPhHigh = (EditText) v.findViewById(R.id.txt_index_ph_high);
        viewHolder.txtLightHigh = (EditText) v.findViewById(R.id.txt_index_light_high);
        viewHolder.txtHumidityHigh = (EditText) v.findViewById(R.id.txt_index_humidity_high);
        viewHolder.txtSoilHigh = (EditText) v.findViewById(R.id.txt_index_soil_high);
        viewHolder.txtTemperatureHigh = (EditText) v.findViewById(R.id.txt_index_temperature_high);
        viewHolder.txtPhLow = (EditText) v.findViewById(R.id.txt_index_ph_low);
        viewHolder.txtLightLow = (EditText) v.findViewById(R.id.txt_index_light_low);
        viewHolder.txtHumidityLow = (EditText) v.findViewById(R.id.txt_index_humidity_low);
        viewHolder.txtSoilLow = (EditText) v.findViewById(R.id.txt_index_soil_low);
        viewHolder.txtTemperatureLow = (EditText) v.findViewById(R.id.txt_index_temperature_low);
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
                        zoneIndex = responseModel;
                        String s = zoneName + " - " + responseModel.getUserPlanName();
                        toolbar.setTitle(s);
                        viewHolder.txtPhHigh.setText(responseModel.getUserHighPh() + "");
                        viewHolder.txtLightHigh.setText(responseModel.getUserHighLight() + "");
                        viewHolder.txtHumidityHigh.setText(responseModel.getUserHighHumidity() + "");
                        viewHolder.txtSoilHigh.setText(responseModel.getUserHighSoilMoisture() + "");
                        viewHolder.txtTemperatureHigh.setText(responseModel.getUserHighTemperature() + "");
                        viewHolder.txtPhLow.setText(responseModel.getUserLowPh() + "");
                        viewHolder.txtLightLow.setText(responseModel.getUserLowLight() + "");
                        viewHolder.txtHumidityLow.setText(responseModel.getUserLowHumidity() + "");
                        viewHolder.txtSoilLow.setText(responseModel.getUserLowSoilMoisture() + "");
                        viewHolder.txtTemperatureLow.setText(responseModel.getUserLowTemperature() + "");
                        viewHolder.txtDateFertilize.setText(responseModel.getUserFertilizeDate());
                        viewHolder.txtWeight.setText(responseModel.getUserWeightFertilize() + "");
                        viewHolder.txtDevideCode.setText(responseModel.getDevideCode());
                        viewHolder.txtLocation.setText(responseModel.getLocation());
                        viewHolder.txtCreate.setText(responseModel.getDateOfGrow());
                        setDobField(viewHolder.txtDateFertilize);
                        setDobField(viewHolder.txtCreate);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_update, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
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
            String plant = zoneIndex.getUserPlanName();
            String typePlant = zoneIndex.getUserTypePlan();
            String zName = zoneName;
            String deviceCode = viewHolder.txtDevideCode.getText().toString();
            String location = viewHolder.txtLocation.getText().toString();
            String dateOfGrow = viewHolder.txtCreate.getText().toString();
            View focus = null;
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
            if (deviceCode == null || deviceCode.length() <= 0) {
                viewHolder.txtDevideCode.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtDevideCode;
                }
            }
            if (location == null || location.length() <= 0) {
                viewHolder.txtLocation.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtLocation;
                }
            }
            if (dateOfGrow == null || dateOfGrow.length() <= 0) {
                viewHolder.txtCreate.setError("This field can not empty!");
                if (focus == null) {
                    focus = viewHolder.txtCreate;
                }
            }

            if (focus != null) {
                focus.requestFocus();
            } else {
                ZoneIndex index = new ZoneIndex(dateFertilizer,
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
                        zoneIndex.getUserLibId(),
                        zoneIndex.getUserPlanName(),
                        zoneIndex.getUserTypePlan(),
                        zoneIndex.getUsername(),
                        location, deviceCode, dateOfGrow);
                restService.getiZoneService().updateZone(index, new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                        getActivity().recreate();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity(), "can not update", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        EditText txtDevideCode;
        EditText txtLocation;
        EditText txtCreate;
    }

    private void setDobField( EditText editText) {
        final  EditText edit = editText;
        final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        final SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss", Locale.US);
        String s = edit.getText().toString();
        final Calendar newCalendar = Calendar.getInstance();
        Date curDate = null;
        try {
            curDate = (Date) dateTimeFormatter.parse(s);
            newCalendar.setTimeInMillis(curDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final String[] dateTime = {"", ""};
        final TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Time time = new Time(hourOfDay, minute, 0);
                dateTime[1] = timeFormatter.format(time);
                edit.setText(dateTime[0] + " " + dateTime[1]);
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
                imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
            }
        });


    }
}
