package com.example.tryingapplication;

import java.util.List;

public class ListItem {

    private String id;
    private String name;
    private String url;
    private String hostThumbnail;
    private String price;
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

    public String getPrice() {
        return price;
    }

    public ListItem(String id, String name, String url, String hostThumbnail, List images, String price) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.hostThumbnail = hostThumbnail;
        this.image_item = images;
        this.price=price;
    }
}
