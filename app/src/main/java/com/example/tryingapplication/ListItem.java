package com.example.tryingapplication;

import java.util.List;

public class ListItem {

    private String id;
    private String name;
    private String url;
    private String hostThumbnail;
    private List<String> image_item;

    private String price;
    private String latitude;
    private String longitude;


    private String daysSelected = "1";

    public String getDaysSelected() {
        return daysSelected;
    }

    public String getPrice() {
        return price;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public List<String> getImage_item() {
        return image_item;
    }

    public String getHostThumbnail() {
        return hostThumbnail;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public ListItem(String id, String name, String url, String hostThumbnail,String price,String latitude, String longitude, List images,String daysSelected) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.hostThumbnail = hostThumbnail;
        this.image_item = images;
        this.latitude=latitude;
        this.longitude=longitude;
        this.price=price;
        this.daysSelected =daysSelected;

    }
}
