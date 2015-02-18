package com.appsbydel.holler;

//TODO: Make the rate and review text a link that will open app store for review.
//TODO: Add logo image to about section
//TODO: Remove buttons, add indicator that explains that user can swipe left / right.

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutFragment extends Fragment {
    int fragVal;
    static AboutFragment init(int val) {
        AboutFragment aboutFragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putInt("val", val);
        aboutFragment.setArguments(args);
        return aboutFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String versionName = BuildConfig.VERSION_NAME;
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        TextView txtVersion = (TextView)view.findViewById(R.id.txtVersion);
        txtVersion.setText("Version " + versionName);
        return view;
    }
}
