package org.naeemrk.fcs.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Naeem <naeemark@gmail.com>
 */
class SpeedUtilTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Calculate Speed: 1km/h")
    void calculateSpeed() {
        double kmPerHour = SpeedUtil.calculateSpeed(3600, 1);
        assertEquals(1, kmPerHour);
    }

    @Test
    @DisplayName("Calculate Distance: 0")
    void haversine_1() {
        double[] latLong= {38.046779, 23.839354};
        double distance = SpeedUtil.haversine(latLong, latLong);
        assertEquals(0, distance);
    }

    @Test
    @DisplayName("Calculate Distance: > 0")
    void haversine_2() {
        double[] from= {38.00, 23.83};
        double[] to= {38.01, 23.84};
        double distance = SpeedUtil.haversine(from, to);
        assertEquals(1.415663074543475, distance);
    }
}