package com.appsbydel.holler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class WootListAdapter extends ArrayAdapter {
    private List<WootDetails> wootDeals;
    private Context context;

    public WootListAdapter(Context context, List<WootDetails> wootDeals) {
        super(context, R.layout.woot_row, wootDeals);
        this.wootDeals = wootDeals;
        this.context = context;
        
    }

    @Override
    public int getCount() {
        return wootDeals.size();
    }

    @Override
    public Object getItem(int position) {
        return wootDeals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View wootView = inflator.inflate(R.layout.woot_row, parent, false);

        WootDetails woot = (WootDetails) getItem(position);
        TextView txtTitle = (TextView)wootView.findViewById(R.id.txtTitle);
        ImageView wootImage = (ImageView)wootView.findViewById(R.id.imgWoot);

        txtTitle.setText(woot.getTitle());
        wootImage.setImageBitmap(woot.getOffer().getPhotos().get(0).getBitmap());
        return wootView;
    }

}
