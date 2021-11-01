package org.naeemrk.fcs.services.filteration;

import javafx.util.Pair;
import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.utils.HaversineUtil;

import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class FiltrationServiceImpl implements FiltrationService {
    @Override
    public boolean isValid(Location location) {
        return false;
    }

    int x=0;

    @Override
    public List<Location> sanitize(List<Location> locationList) {
        for (int i = 0; i < locationList.size() - 1; i++) {
            Location currentLocation = locationList.get(i);
            Location nextLocation = locationList.get(i + 1);
            if (currentLocation.getRideId() == nextLocation.getRideId()) {
                Pair<Double, Double> current = new Pair<>(currentLocation.getLatitude(), currentLocation.getLongitude());
                Pair<Double, Double> next = new Pair<>(nextLocation.getLatitude(), nextLocation.getLongitude());

                double haversine = HaversineUtil.haversine(current, next);
                double kmPerHour = calculateSpeed(currentLocation.getEpochTimestamp(), nextLocation.getEpochTimestamp(), haversine);
                if (kmPerHour > 100)
                    System.out.printf("Invalid Count: %d, A: %s, \tB: %s, \tdistance:%f time:%ds kmPerHour:%f %n",x++, currentLocation, nextLocation, haversine, (nextLocation.getEpochTimestamp() - currentLocation.getEpochTimestamp()), kmPerHour);
            }
        }
        return locationList;
    }

    private double calculateSpeed(long firstTimestamp, long secondTimestamp, double distanceInKm) {
        long seconds = secondTimestamp - firstTimestamp;
        double distanceInMeters = distanceInKm * 1000;
        return 3.6 * (distanceInMeters / seconds);
    }
}
