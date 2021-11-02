package org.naeemrk.fcs.services.filtration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.utils.SpeedUtil;

import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class FiltrationServiceImpl implements FiltrationService {

    private static final Logger logger = LogManager.getLogger(FiltrationServiceImpl.class);

    @Override
    public void sanitize(List<Location> locationList) {
        logger.info("Initial Size: {}", locationList.size());
        for (int i = 1; i < locationList.size(); i++) {
            Location currentLocation = locationList.get(i);
            Location lastLocation = locationList.get(i - 1);

            double speed = calculateSpeed(currentLocation, lastLocation);
            currentLocation.setSpeedKmPerHour(speed);

            if (!currentLocation.isValid()) {
                locationList.remove(currentLocation);
                i--; // reduce counter to recalculate for new adjacent locations after removal of an element
                logger.debug("Removed: " + currentLocation + " - Current Size:" + locationList.size());
            }
        }
        logger.debug("done with: " + locationList.get(0).getRideId() + " - Size:" + locationList.size() + "\n");
    }

    @Override
    public double calculateSpeed(Location currentLocation, Location lastLocation) {
        double[] current = {currentLocation.getLatitude(), currentLocation.getLongitude()};
        double[] last = {lastLocation.getLatitude(), lastLocation.getLongitude()};

        double distanceInKm = SpeedUtil.haversine(last, current);
        double kmPerHour = SpeedUtil.calculateSpeed(currentLocation.getEpochTimestamp(), lastLocation.getEpochTimestamp(), distanceInKm);
        logger.info("Current {}, Last {}, km:{} time:{}s km/h:{}", currentLocation, lastLocation, distanceInKm, (currentLocation.getEpochTimestamp() - lastLocation.getEpochTimestamp()), kmPerHour);
        return kmPerHour;
    }

}