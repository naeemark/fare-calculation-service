package org.naeemrk.fcs.models;

import lombok.*;

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
