package org.naeemrk.fcs.services.ingestion;

import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.models.RideFare;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Naeem <naeemark@gmail.com>
 */
class IngestionServiceImplTest {

    private final IngestionService service = new IngestionServiceImpl();

    private final String filePath = "src/test/resources/data/paths.csv";

    @Test
    @DisplayName("Read data from provided file name")
    void read_pass() {
        List<Location> locationList = service.read(filePath);
        assertNotNull(locationList);
        assertEquals(50, locationList.size());
        assertEquals(1, locationList.get(0).getRideId());
    }

    @Test
    @DisplayName("Read data from provided invalid file name")
    void read_fail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.read("filePath"));
        assertNotNull(exception);
    }

    @Test
    @DisplayName("Read data from provided invalid file")
    void read_fail_2() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.read(filePath.substring(0, filePath.lastIndexOf("/"))));
        assertNotNull(exception);
    }

    @Test
    @DisplayName("Write to output file - pass")
    void write_pass() {
        List<RideFare> rideFares = Collections.singletonList(new RideFare(1, 100));
        service.write(filePath + ".output", rideFares);
    }

    @Test
    @DisplayName("Write to output file")
    void write_fail() {
        List<RideFare> rideFares = Collections.singletonList(new RideFare(1, 100));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.write("filePath", rideFares));
        assertNotNull(exception);
    }

    @Test
    @DisplayName("Test splitting into multiple lists based upon ride_id ")
    void split() {
        List<Location> locationList = Arrays.asList(
                new Location(1, 0, 0, 0),
                new Location(1, 0, 0, 0),
                new Location(2, 0, 0, 0)
        );
        List<List<Location>> split = service.split(locationList);
        assertNotNull(split);
        assertEquals(2, split.size());
        assertEquals(1, split.get(0).get(0).getRideId());
        assertEquals(2, split.get(1).get(0).getRideId());
    }
}