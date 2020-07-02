package com.example.workcellsystem.data;

public enum HttpEnum {

    Default("http://keyonecn.com:8897/");

    private String url;

    HttpEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
