package com.sangpt.smartgarden.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sangpt.smartgarden.fragment.ActuatorFragment;
import com.sangpt.smartgarden.fragment.IndexFragment;
import com.sangpt.smartgarden.fragment.SensorFragment;

public class HomeTabAdapter extends FragmentPagerAdapter {
    int zoneId;
    String zoneName;
    public HomeTabAdapter(FragmentManager fragmentManager, int zoneId, String zoneName) {
        super(fragmentManager);
        this.zoneId = zoneId;
        this.zoneName = zoneName;
    }


    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("zoneId",zoneId);
        bundle.putString("zoneName",zoneName);
        switch (position) {
            case 0:
                IndexFragment indexFragment = new IndexFragment();
                indexFragment.setArguments(bundle);
                return indexFragment;
            case 1:
                SensorFragment sensorFragment = new SensorFragment();
                sensorFragment.setArguments(bundle);
                return sensorFragment;
            case 2:
                ActuatorFragment actuatorFragment = new ActuatorFragment();
                actuatorFragment.setArguments(bundle);
                return actuatorFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Index";
            case 1:
                return "Sensor";
            case 2:
                return "Actuator";
        }
        return null;
    }

}
