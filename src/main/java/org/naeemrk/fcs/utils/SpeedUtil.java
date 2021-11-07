package org.naeemrk.fcs.utils;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class SpeedUtil {

    private SpeedUtil() {
    }

    /**
     * Calculates km/hour
     *
     * @param duration     Seconds
     * @param distanceInKm for current Location Object
     * @return km/h
     */
    public static double calculateSpeed(long duration, double distanceInKm) {
        double distanceInMeters = distanceInKm * 1000;
        return 3.6 * (distanceInMeters / duration);
    }

    /**
     * Calculates and returns distance between to pairs of Latitude and longitude in KM
     *
     * @param from Pair of latitude, longitude
     * @param to   Pair of latitude, longitude
     * @return double
     */
    public static double haversine(double[] from, double[] to) {
        return haversine(from[0], from[1], to[0], to[1]);
    }

    /**
     * Calculates and returns distance between to pairs of Latitude and longitude in KM
     *
     * @param latitudeA  double
     * @param longitudeA double
     * @param latitudeB  double
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
