package com.appsbydel.holler;

import org.apache.http.message.BasicNameValuePair;
import java.util.List;

public class Item
{
    private String salePrice;
    private List<BasicNameValuePair> attributes;
    public String getSalePrice(){
        return salePrice;
    }
    public void setSalePrice(String price)
    {
        salePrice = price;
    }
    public List<BasicNameValuePair> getAttributes()
    {
        return attributes;
    }
    public void setAttributes(List<BasicNameValuePair> list)
    {
        attributes = list;
    }

    public Item()
    {

    }
}
