package org.naeemrk.fcs.utils;

import javafx.util.Pair;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class HaversineUtil {

    /**
     * Calculates and return between to pairs of Latitude and longitude in KM
     *
     * @param current Pair of latitude, longitude
     * @param next Pair of latitude, longitude
     * @return double
     */
    public static double haversine(Pair<Double, Double> current, Pair<Double, Double> next) {
        return haversine(current.getKey(), current.getValue(), next.getKey(), next.getValue());
    }

    /**
     * Calculates and return between to pairs of Latitude and longitude in KM
     *
     * @param latitudeA double
     * @param longitudeA double
     * @param latitudeB double
     * @param longitudeB double
     * @return double
     */
    private static double haversine(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(latitudeB - latitudeA);
        double dLon = Math.toRadians(longitudeB - longitudeA);

        // convert to radians
        latitudeA = Math.toRadians(latitudeA);
        latitudeB = Math.toRadians(latitudeB);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(latitudeA) *
                        Math.cos(latitudeB);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
}
