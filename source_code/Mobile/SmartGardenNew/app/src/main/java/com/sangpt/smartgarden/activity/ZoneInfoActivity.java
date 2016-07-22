package com.sangpt.smartgarden.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.sangpt.smartgarden.R;
import com.sangpt.smartgarden.adapter.HomeTabAdapter;
import com.sangpt.smartgarden.listener.ICallActivity;

public class ZoneInfoActivity extends AppCompatActivity implements ICallActivity{
    private ViewHolder viewHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Chi tiết");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getActionBar();

        int zoneId = getIntent().getIntExtra("zoneId",-1);
        String zoneName = getIntent().getStringExtra("zoneName");
        toolbar.setTitle(zoneName.toUpperCase());

        viewHolder = new ViewHolder();
        viewHolder.tabLayout = (TabLayout) findViewById(R.id.tabs_home);
        viewHolder.viewPager = (ViewPager) findViewById(R.id.viewpager_home);
        viewHolder.viewPager.setAdapter(new HomeTabAdapter(getSupportFragmentManager(),zoneId,zoneName));
        viewHolder.tabLayout.post(new Runnable() {
            @Override
            public void run() {
                viewHolder.tabLayout.setupWithViewPager(viewHolder.viewPager);
            }
        });

    }

    @Override
    public void reCreateActivity() {
        recreate();
    }

    private final class ViewHolder{
        ViewPager viewPager;
        TabLayout tabLayout;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }

}
