package org.naeemrk.fcs.services.calculation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.models.RideFare;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.naeemrk.fcs.common.Constants.FARE_MIN_AMOUNT;

/**
 * @author Naeem <naeemark@gmail.com>
 */
class CalculationServiceImplTest {

    // not peak = 1636200000
    private long peakTimeEpoch = 1636302000;

    private final CalculationService service = new CalculationServiceImpl();
    private final List<Location> locationList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        long rideId = 1;
        for (int i = 0; i < 5; i++) locationList.add(new Location(rideId, 0, 0, 0, 0, 0, 10));
        for (int i = 0; i < 20; i++) locationList.add(new Location(rideId, 0, 0, 0, .3, 50, 10));
    }

    @Test
    void calculate_baseFare() {
        RideFare rideFare = service.calculate(locationList.subList(0, 10));
        assertNotNull(rideFare);
        assertEquals(FARE_MIN_AMOUNT, rideFare.getFareEstimate());
    }

    @Test
    void calculate_peakTime() {
        double expectedFareAmount = 9.27;
        locationList.forEach(location -> location.setEpochTimestamp(peakTimeEpoch += 10));
        RideFare rideFare = service.calculate(locationList);
        assertNotNull(rideFare);
        assertEquals(expectedFareAmount, rideFare.getFareEstimate());
    }
}