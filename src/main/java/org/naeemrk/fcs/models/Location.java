package org.naeemrk.fcs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Naeem <naeemark@gmail.com>
 */
@Data
@AllArgsConstructor
public class Location {
    private long rideId;
    private double latitude;
    private double longitude;
    private long timestamp;
}
