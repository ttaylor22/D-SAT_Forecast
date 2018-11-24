package com.example.ttaylor.weatherapp.Modelv2;


import java.util.List;

public class WeatherForecastResult {

    private String cod;
    private double message;
    private int cnt;
    private List<com.example.ttaylor.weatherapp.Modelv2.List> list;
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<com.example.ttaylor.weatherapp.Modelv2.List> getList() {
        return list;
    }

    public void setList(List<com.example.ttaylor.weatherapp.Modelv2.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


}
