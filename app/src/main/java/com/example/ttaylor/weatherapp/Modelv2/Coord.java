package com.example.ttaylor.weatherapp.Modelv2;

public class Coord {
    private String lon;
    private String lat;

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString(){
        return new StringBuilder("[").append(this.lat).append(",").append(this.lon).append("]").toString();
    }
}
