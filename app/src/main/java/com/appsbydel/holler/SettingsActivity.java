package com.appsbydel.holler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class SettingsActivity extends FragmentActivity {

    private static final int NUM_ITEMS = 2;
    private ActivityPagerAdapter adapter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        adapter = new ActivityPagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
}

public static class ActivityPagerAdapter extends FragmentPagerAdapter {
    public ActivityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SettingsFragment.init(position);
            case 1:
                return AboutFragment.init(position);
            default:
                return SettingsFragment.init(position);
        }
    }
}
}


