package com.example.tryingapplication;

import com.google.gson.JsonArray;

import java.util.List;

public class ListItem {

    private String id;
    private String name;
    private String url;
    private String hostThumbnail;
    private List<String> image_item;

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

    public ListItem(String id, String name, String url, String hostThumbnail,List images) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.hostThumbnail = hostThumbnail;
        this.image_item = images;
    }
}
