package com.appsbydel.holler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONHandler {
    public List<WootDetails> JsonToWoot(String result) {
        List<WootDetails> allWoots = new ArrayList<WootDetails>();
        try {
            JSONArray woots = new JSONArray(result);
            JSONArray photos;
            JSONObject obj;
            for (int i = 0; i < woots.length(); i++) {
                obj = woots.getJSONObject(i);
                JSONObject offer = (JSONObject) obj.getJSONArray("Offers").get(0);
                JSONArray photosArray = (JSONArray) offer.getJSONArray("Photos");
                JSONArray itemsArray = (JSONArray) offer.getJSONArray("Items");
                WootDetails woot = new WootDetails();
                woot.setSite(obj.getString("Site"));
                woot.setTitle(obj.getString("Title"));
                woot.setOffer(CreateOffer(offer));
                woot.getOffer().setPhotos(CreatePhotoList(photosArray));
                woot.getOffer().setItems(CreateItemList(itemsArray));
                allWoots.add(woot);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allWoots;
    }

    private List<Item> CreateItemList(JSONArray itemsArray) throws JSONException {
        List<Item> Items = new ArrayList<Item>();
        JSONObject obj;
        for (int i = 0; i < itemsArray.length(); i++) {
            obj = itemsArray.getJSONObject(i);
            Item item = new Item();
            item.setSalePrice(obj.getString("SalePrice"));
            List<BasicNameValuePair> l = new ArrayList<BasicNameValuePair>();
            JSONArray attributesArray = (JSONArray) obj.optJSONArray("Attributes");
            if(null != attributesArray){
                JSONObject attObj;
                for (int a = 0; a < attributesArray.length(); a++) {
                    attObj = attributesArray.getJSONObject(i);
                    BasicNameValuePair p = new BasicNameValuePair(attObj.getString("Key"), attObj.getString("Value"));
                    l.add(p);
                    item.setAttributes(l);
                }
            }
            Items.add(item);
        }
        return Items;
    }

    private List<Photo> CreatePhotoList(JSONArray photosArray) throws JSONException {
        List<Photo> photos = new ArrayList<Photo>();
        JSONObject obj;
        for (int i = 0; i < photosArray.length(); i++) {
            obj = photosArray.getJSONObject(i);
            Photo photo = new Photo();
            photo.setUrl(obj.getString("Url"));
            photo.setWidth(Integer.parseInt(obj.getString("Width")));
            photos.add(photo);
        }
        return photos;
    }

    private Offer CreateOffer(JSONObject offer) throws JSONException {
        Offer o = new Offer();
        o.setSpecs(offer.optString("Specs"));
        o.setUrl(offer.getString("Url"));
        o.setWriteUp(offer.getString("WriteUp"));
        return o;
    }
}
