package org.naeemrk.fcs.services.calculation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.models.RideFare;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.naeemrk.fcs.common.Constants.FARE_MIN_AMOUNT;

/**
 * @author Naeem <naeemark@gmail.com>
 */
class CalculationServiceImplTest {

    private long peakTimeEpoch;
    private long offPeakTimeEpoch;

    private final CalculationService service = new CalculationServiceImpl();
    private final List<Location> locationList = new ArrayList<>();

    void setUpTimeEpochs() {
        ZoneId zone = ZoneOffset.systemDefault();
        ZonedDateTime zdt = ZonedDateTime.now(zone);
        zdt = zdt.toLocalDate().atStartOfDay(zone);
        long midNight = zdt.toInstant().toEpochMilli() / 1000;
        peakTimeEpoch = midNight + 10000;
        offPeakTimeEpoch = peakTimeEpoch + 20000;
    }

    @BeforeEach
    void setUp() {
        setUpTimeEpochs();
        long rideId = 1;
        for (int i = 0; i < 5; i++) locationList.add(new Location(rideId, 0, 0, 0, 0, 0, 10));
        for (int i = 0; i < 20; i++) locationList.add(new Location(rideId, 0, 0, 0, .3, 50, 10));
    }

    @Test
    @DisplayName("Calculate with BaseFare")
    void calculate_baseFare() {
        RideFare rideFare = service.calculate(locationList.subList(0, 10));
        assertNotNull(rideFare);
        assertEquals(FARE_MIN_AMOUNT, rideFare.getFareEstimate());
    }

    @Test
    @DisplayName("Calculate with PeakTime Rate")
    void calculate_peakTime() {
        double expectedFareAmount = 9.27;
        locationList.forEach(location -> location.setEpochTimestamp(peakTimeEpoch += 10));
        RideFare rideFare = service.calculate(locationList);
        assertNotNull(rideFare);
        assertEquals(expectedFareAmount, rideFare.getFareEstimate());
    }

    @Test
    @DisplayName("Calculate with OffPeakTime Rate")
    void calculate_offPeakTime() {
        double expectedFareAmount = 5.91;
        locationList.forEach(location -> location.setEpochTimestamp(offPeakTimeEpoch += 10));
        RideFare rideFare = service.calculate(locationList);
        assertNotNull(rideFare);
        assertEquals(expectedFareAmount, rideFare.getFareEstimate());
    }
}