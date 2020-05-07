package com.example.h9;

import androidx.annotation.NonNull;

public class SmartPost {

    private String name, city, address, availability;

    public SmartPost(String n, String c, String ad, String av) {
        name = n;
        city = c;
        address = ad;
        availability = av;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getAvailability() {
        return availability;
    }

    @NonNull
    @Override
    public String toString() {
        String info = name;
        return info;
    }
}
