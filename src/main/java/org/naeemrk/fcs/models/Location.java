package org.naeemrk.fcs.models;

import lombok.Data;

import static org.naeemrk.fcs.common.Constants.ALLOWED_MAX_KM_PER_HOUR;
import static org.naeemrk.fcs.common.Constants.MIN_KM_PER_HOUR;

/**
 * @author Naeem <naeemark@gmail.com>
 */
@Data
public class Location {
    private long rideId;
    private double latitude;
    private double longitude;
    private long epochTimestamp;
    private double speedKmPerHour = -1;

    public Location(long rideId, double latitude, double longitude, long epochTimestamp) {
        this.rideId = rideId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.epochTimestamp = epochTimestamp;
    }

    public boolean isValid() {
        return this.getSpeedKmPerHour() >= MIN_KM_PER_HOUR && this.getSpeedKmPerHour() <= ALLOWED_MAX_KM_PER_HOUR;
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
