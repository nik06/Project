package com.khurana.nikhil.tuhub;

import com.khurana.nikhil.tuhub.EventRegister;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsMain extends FragmentPagerAdapter {

    public TabsMain(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new listclick();
            case 1:
                // Games fragment activity
                return new SocietyDetails();

        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}
