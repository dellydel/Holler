package com.appsbydel.holler;

import java.util.ArrayList;
import java.util.List;

public class Offer
{
    private List<Item> items;
    private String url;
    private String writeUp;
    private String specs;
    private List<Photo> photos;

    public List<Item> getItems() { return items; }
    public String getUrl() { return url; }
    public String getWriteUp() {return writeUp; }
    public String getSpecs() { return specs; }
    public List<Photo> getPhotos() { return photos; }
    public List<Photo> getFilteredPhotos() {
        List<Photo> filtered = new ArrayList<Photo>();
        if (photos.size() > 0)
            for (Photo p : photos.subList(3, photos.size()))
                if (p.getWidth() > 150) filtered.add(p);
        return filtered;
    }
    public String getDescription()
    {
        return "<b>" + getItems().get(0).getSalePrice() + "</b>" + "<br/><br/><br/>"
                + "<div>" + getWriteUp() + "</div>" + "<br/><br/>" + "<div>" + getSpecs() + "</div>";
    }

    public void setItems(List<Item> itemList) { items = itemList; }
    public void setUrl(String urlValue) { url = urlValue; }
    public void setWriteUp(String writeUpValue) { writeUp = writeUpValue; }
    public void setSpecs(String specsValue) { specs = specsValue; }
    public void setPhotos(List<Photo> photoList) { photos = photoList; }

    public Offer()
    { }
}
