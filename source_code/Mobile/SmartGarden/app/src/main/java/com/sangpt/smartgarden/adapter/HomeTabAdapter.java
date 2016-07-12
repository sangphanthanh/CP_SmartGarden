package com.sangpt.smartgarden.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sangpt.smartgarden.fragment.ActuatorFragment;
import com.sangpt.smartgarden.fragment.IndexFragment;
import com.sangpt.smartgarden.fragment.SensorFragment;

public class HomeTabAdapter extends FragmentPagerAdapter {
    public HomeTabAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new IndexFragment();
            case 1:
                return new SensorFragment();
            case 2:
                return new ActuatorFragment();
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
