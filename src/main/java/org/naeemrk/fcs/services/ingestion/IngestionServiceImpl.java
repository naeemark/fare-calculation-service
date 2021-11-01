package org.naeemrk.fcs.services.ingestion;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.naeemrk.fcs.models.Location;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class IngestionServiceImpl implements IngestionService {

    @Override
    public List<Location> read(String filePath) {

        // Validates if the file exists
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("Provided file path is not valid");
        }

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
                long timestamp = Long.parseLong(record.get(3));

                Location person = new Location(rideId, latitude, longitude, timestamp);
                list.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
