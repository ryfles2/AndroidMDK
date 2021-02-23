package com.wfis.wfis_shop.models;

public class Map {
    public String title;
    public Double lat;
    public Double lng;

    public Map() {
        this.title = "Łódzki Dom Kultury";
        this.lat = 51.77064423629994;
        this.lng = 19.472689898306754;
    }

    @Override
    public String toString() {
        return "Map{" +
                "title='" + title + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public Map(String title, Double lat, Double lng) {
        this.title = title;
        this.lat = lat;
        this.lng = lng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
