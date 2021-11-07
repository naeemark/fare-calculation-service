package org.naeemrk.fcs.services.ingestion;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.models.RideFare;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class IngestionServiceImpl implements IngestionService {

    private static final Logger logger = LogManager.getLogger(IngestionServiceImpl.class);

    @Override
    public List<Location> read(String filePath) {
        validateFilePath(filePath);
        Path path = Paths.get(filePath);

        List<Location> list = Collections.emptyList();
        try {
            int initialCapacity = (int) Files.lines(path).count();
            list = new ArrayList<>(initialCapacity);

            BufferedReader reader = Files.newBufferedReader(path);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(reader);
            for (CSVRecord record : records) {
                long rideId = Long.parseLong(record.get(0));
                double latitude = Double.parseDouble(record.get(1));
                double longitude = Double.parseDouble(record.get(2));
                long epochTimestamp = Long.parseLong(record.get(3));

                Location person = new Location(rideId, latitude, longitude, epochTimestamp);
                list.add(person);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public void write(String filePath, List<RideFare> rideFares) {
        validateFilePath(filePath);
        Path path = Paths.get(filePath);
        try (final CSVPrinter printer = CSVFormat.RFC4180.withHeader("id_ride", "fare_estimate").print(path, StandardCharsets.UTF_8)) {
            for (RideFare rideFare : rideFares) {
                printer.printRecord(rideFare.getRideId(), rideFare.getFareEstimate());
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<List<Location>> split(List<Location> locationList) {
        Map<Long, List<Location>> map = new HashMap<>();
        locationList.forEach(e -> {
            long rideId = e.getRideId();
            if (!map.containsKey(rideId)) {
                map.put(rideId, new ArrayList<>());
            }
            map.get(rideId).add(e);
        });
        return new ArrayList<>(map.values());
    }

    /**
     * Validates if the file exists
     *
     * @param filePath String
     */
    private void validateFilePath(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("Provided file path is not valid: " + filePath);
        }
    }
}
