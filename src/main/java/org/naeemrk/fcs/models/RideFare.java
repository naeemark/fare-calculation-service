package org.naeemrk.fcs.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.DecimalFormat;

import static org.naeemrk.fcs.common.Constants.*;

/**
 * @author Naeem <naeemark@gmail.com>
 */
@Data
@AllArgsConstructor
public class RideFare {
    private long rideId;
    private double fareEstimate;

    /**
     * Formates and return the fare in two decimal points
     * @return double
     */
    public double getFareEstimate() {
        DecimalFormat format = new DecimalFormat("#.##");
        return Double.parseDouble(format.format(this.fareEstimate));
    }
}
