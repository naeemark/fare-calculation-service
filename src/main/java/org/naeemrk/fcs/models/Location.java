package org.naeemrk.fcs.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import static org.naeemrk.fcs.common.Constants.*;

/**
 * @author Naeem <naeemark@gmail.com>
 */
@Data
@AllArgsConstructor
public class Location {
    private long rideId;
    private double latitude;
    private double longitude;
    private long epochTimestamp;

    // Extra attributes
    private double distanceInKm;
    private double speedKmPerHour;
    private long duration;

    public Location(long rideId, double latitude, double longitude, long epochTimestamp) {
        this.rideId = rideId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.epochTimestamp = epochTimestamp;
    }

    public boolean isValid() {
        return this.getSpeedKmPerHour() >= MIN_KM_PER_HOUR && this.getSpeedKmPerHour() <= ALLOWED_MAX_KM_PER_HOUR;
    }

    public boolean isIdle() {
        return this.getSpeedKmPerHour() <= IDLE_KM_PER_HOUR;
    }

    @Override
    public String toString() {
        return "Location{" +
                "rideId=" + rideId +
                ", lat=" + latitude +
                ", long=" + longitude +
                ", time=" + epochTimestamp +
                '}';
    }
}
