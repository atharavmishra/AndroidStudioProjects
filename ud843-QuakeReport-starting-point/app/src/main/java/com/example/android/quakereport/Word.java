package com.example.android.quakereport;

public class Word {
    private String mag;
    private String city;
    private String date;



    private String time;
    public Word(String mag, String city, String date,String time) {
        this.mag = mag;
        this.city = city;
        this.date = date;
        this.time=time;
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


}
