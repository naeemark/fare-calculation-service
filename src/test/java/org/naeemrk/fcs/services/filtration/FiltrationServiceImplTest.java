package org.naeemrk.fcs.services.filtration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.services.ingestion.IngestionService;
import org.naeemrk.fcs.services.ingestion.IngestionServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Naeem <naeemark@gmail.com>
 */
class FiltrationServiceImplTest {

    private final FiltrationService service = new FiltrationServiceImpl();
    private List<Location> locationList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        long rideId = 1;
        locationList.add(new Location(rideId, 0, 0, 0));
        locationList.add(new Location(rideId, 0, 0, 0));
        locationList.add(new Location(rideId, 0, 0, 0));
        locationList.add(new Location(rideId, 37.968840, 23.725827, 1636294805));
        locationList.add(new Location(rideId, 37.968850, 23.725829, 1636294815));
    }

    @Test
    @DisplayName("Sanitize list of same locations")
    void sanitize_all_same() {
        locationList = locationList.subList(0, 2);
        service.sanitize(locationList);
        assertEquals(1, locationList.size());
    }

    @Test
    @DisplayName("Sanitize list of locations")
    void sanitize() {
        service.sanitize(locationList);
        assertEquals(3, locationList.size());
    }
}