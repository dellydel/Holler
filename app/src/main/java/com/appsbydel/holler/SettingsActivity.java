package com.appsbydel.holler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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

        Button button = (Button) findViewById(R.id.btnSettings);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pager.setCurrentItem(0);
            }
        });

        button = (Button) findViewById(R.id.btnAbout);
        button.setOnClickListener(new OnClickListener() {
        public void onClick (View v){
            pager.setCurrentItem(1);
        }
    }
    );
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


