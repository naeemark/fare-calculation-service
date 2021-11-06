package org.naeemrk.fcs.services.calculation;

import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.models.RideFare;
import org.naeemrk.fcs.utils.TimeUtil;

import java.util.List;

import static org.naeemrk.fcs.common.Constants.*;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class CalculationServiceImpl implements CalculationService {

    @Override
    public RideFare calculate(List<Location> locationList) {
        double fare = FARE_FLAT_AMOUNT; // Initialize with Flat amount
        fare += locationList.stream().mapToDouble(this::calculateFareAmount).sum();
        return new RideFare(locationList.get(0).getRideId(), Math.max(fare, FARE_MIN_AMOUNT));
    }

    private double calculateFareAmount(Location location) {
        boolean idle = location.isIdle();
        if (idle) {
            return location.getDuration() * (FARE_IDLE_PER_HOUR / 3600);
        } else {
            return calculateMovingFareAmount(location);
        }
    }

    private double calculateMovingFareAmount(Location location) {
        boolean isPeakTime = TimeUtil.isPeekTime(location.getEpochTimestamp());
        if (isPeakTime) {
            return location.getDistanceInKm() * FARE_MOVING_PER_KM_PEEK;
        } else {
            return location.getDistanceInKm() * FARE_MOVING_PER_KM_NORMAL;
        }
    }
}
