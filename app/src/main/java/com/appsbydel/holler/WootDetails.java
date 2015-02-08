package com.appsbydel.holler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WootDetails
{
    private String title;
    private String site;
    private List<Offer> offers;
    private Offer offer;

    public String getTitle() { return title; }
    public String getSite() { return site; }
    public List<Offer> getOffers() { return offers; }
    public Offer getOffer() { return offer; }
    public Integer getSortOrder() {
        if (site == "www.woot.com")
        {
            return 1;
        }
        return 2;
    }
    public String getSize(){
        if (site == "www.woot.com")
        { return "large"; }
        return "medium";
    }
    public String getNewSite()
    {
        if (site != "www.woot.com") {
            Integer dot;
            dot = site.indexOf('.');
            return site.substring(0, dot);
        }
        return "woot";
    }
    public String getTitlePrice()
    {
        String price = offers.get(0).getItems().get(0).getSalePrice();
        NumberFormat format =
                NumberFormat.getCurrencyInstance(Locale.US);
        String currency = format.format(price);
        return title + "     " + currency;
    }

    public void setTitle(String titleValue){title = titleValue;}
    public void setSite(String siteValue){site = siteValue;}
    public void setOffers(List<Offer> offersList) {offers = offersList;}
    public void setOffer(Offer offerValue) {offer = offerValue;}
}








