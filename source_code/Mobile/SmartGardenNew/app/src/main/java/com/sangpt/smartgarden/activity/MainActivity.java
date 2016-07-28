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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.adapter.ZonesAdapter;
import com.sangpt.smartgarden.model.model.Zone;
import com.sangpt.smartgarden.model.responseModel.GetZonesResponseModel;
import com.sangpt.smartgarden.services.RestService;
import com.sangpt.smartgarden.utils.DataUtils;
import com.sangpt.smartgarden.utils.QuickSharePreference;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private ViewHolder viewHolder;
    private ZonesAdapter zonesAdapter;
    private RestService restService;
    private List<Zone> zones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Zone List");
        setSupportActionBar(toolbar);
        init();
        event();
        
    }

    private void event() {
        
        viewHolder.gvZones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataUtils.getAlphaAmination(view);
                Zone zone = zonesAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this,ZoneInfoActivity.class);
                intent.putExtra("zoneId",zone.getZoneId());
                intent.putExtra("zoneName",zone.getZoneName());
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
            }
        });

        viewHolder.gvZones.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Warning");
                final Zone zone = zonesAdapter.getItem(position);
                builder.setMessage("Do you want to delete this zone");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final DialogInterface finalDialogInterface = dialog;
                        restService.getiZoneService().deleteZone(zone.getZoneId(), new Callback<String>() {
                            @Override
                            public void success(String s, Response response) {
                                Toast.makeText(MainActivity.this, "This zone is deleted", Toast.LENGTH_SHORT).show();
                                finalDialogInterface.dismiss();
                                getList();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(MainActivity.this, "Some thing wrong. Please check your connection", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                return false;
            }
        });
    }

    private void init() {
        viewHolder = new ViewHolder();
        restService = new RestService();
        zones = new ArrayList<>();
        viewHolder.gvZones = (GridView) findViewById(R.id.gv_zones);
        zonesAdapter = new ZonesAdapter(this,R.layout.item_zone,zones);
        viewHolder.gvZones.setAdapter(zonesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_zone) {
            Intent intent = new Intent(MainActivity.this, EnterZoneActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in,R.anim.left_out);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getList() {

        restService.getiZoneService().getZones(DataUtils.getINSTANCE(this).getmPreferences().getString(QuickSharePreference.SHARE_USERNAME, ""), new Callback<GetZonesResponseModel>() {
            @Override
            public void success(GetZonesResponseModel responseModel, Response response) {
                if (responseModel.getZones()!=null){
                    zones = responseModel.getZones();
                    zonesAdapter.setZones(zones);
                }else{
                    Toast.makeText(MainActivity.this, "Không có khu nào cả!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "Lỗi rồi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class ViewHolder{
        GridView gvZones;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }
}
