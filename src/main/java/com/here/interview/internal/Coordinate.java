package com.here.interview.internal;

public class Coordinate {
    public double lat;
    public double lng;

    public String name;

    private Coordinate() {
    }

    private Coordinate(double lat, double lng, String name) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }

    public static Coordinate of(double lat, double lng, String name) {
        return new Coordinate(lat, lng, name);
    }
}
