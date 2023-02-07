package com.example.tryingapplication;

public class ListItem {

    private String id;
    private String name;
    private String url;
    private String hostThumbnail;

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

    public ListItem(String id, String name, String url, String hostThumbnail) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.hostThumbnail = hostThumbnail;
    }
}
