package com.appsbydel.holler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;


class WootListAdapter extends ArrayAdapter {
    private List<WootDetails> wootDeals;
    //private Context context;

    public WootListAdapter(Context context, List<WootDetails> wootDeals) {
        super(context, R.layout.woot_row, wootDeals);
        this.wootDeals = wootDeals;
        //this.context = context;
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
        TextView txtSite = (TextView)wootView.findViewById(R.id.txtSite);
        ImageView wootImage = (ImageView)wootView.findViewById(R.id.imgWoot);

        txtTitle.setText(woot.getTitle());
        txtSite.setText(woot.getSite());

        new DownloadImageTask(wootImage).execute(woot.getOffer().getPhotos().get(0).getUrl());

        //wootImage.setImageBitmap(woot.getOffer().getPhotos().get(0).getBitmap());
        return wootView;
    }

    //Downloads images based on url obtained in prior webservice call
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
