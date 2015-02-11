package com.appsbydel.holler;

import android.graphics.Bitmap;

public class Photo
{
    private String photoUrl;
    private int width;
    private Bitmap bm;

    public String getUrl() { return photoUrl; }
    public int getWidth() { return width; }
    public Bitmap getBitmap() {return bm; }

    public void setWidth(int widthValue) {width = widthValue;}
    public void setUrl(String urlValue)   {photoUrl = urlValue; }

    public Photo()
    { }
}