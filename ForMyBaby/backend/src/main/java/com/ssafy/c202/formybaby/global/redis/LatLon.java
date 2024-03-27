package com.ssafy.c202.formybaby.global.redis;

import lombok.Data;

@Data
public class LatLon {
    private static final double EARTH_RADIUS = 6371.0;

    private Double lat;
    private Double lon;

    public LatLon(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getDistance(LatLon a) {
        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(a.getLat());
        double lon1Rad = Math.toRadians(a.getLon());
        double lat2Rad = Math.toRadians(this.getLat());
        double lon2Rad = Math.toRadians(this.getLon());

        // Calculate differences in latitude and longitude
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Calculate the square of half the chord length between the points
        double x = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.pow(Math.sin(deltaLon / 2), 2);

        // Calculate the angular distance in radians
        double c = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1 - x));

        return EARTH_RADIUS * c;
    }

}
