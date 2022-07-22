package com.example.android.quakereport;

public class Word {
    private String mag;
    private String city;
    private String date;
    private String url;



    private String time;
    public Word(String mag, String city, String date,String time,String url) {
        this.mag = mag;
        this.city = city;
        this.date = date;
        this.time=time;
        this.url=url;
    }

    public String getMag() {
        return mag;
    }
    public String getCity() {
        return city;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getUrl() {
        return url;
    }
}
