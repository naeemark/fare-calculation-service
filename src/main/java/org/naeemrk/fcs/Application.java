package org.naeemrk.fcs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.models.RideFare;
import org.naeemrk.fcs.services.calculation.CalculationService;
import org.naeemrk.fcs.services.calculation.CalculationServiceImpl;
import org.naeemrk.fcs.services.filtration.FiltrationService;
import org.naeemrk.fcs.services.filtration.FiltrationServiceImpl;
import org.naeemrk.fcs.services.ingestion.IngestionService;
import org.naeemrk.fcs.services.ingestion.IngestionServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No File Path is provided!");
        }
        String filePath = args[0];
        new Application().process(filePath);
    }

    private void process(String filePath) {
        Map<Long, String> summary = new TreeMap<>();

        IngestionService ingestionService = new IngestionServiceImpl();
        List<Location> locationList = ingestionService.read(filePath);
        List<List<Location>> rideLists = ingestionService.split(locationList);
        rideLists.forEach(e -> summary.put(e.get(0).getRideId(), String.valueOf(e.size())));

        FiltrationService filtrationService = new FiltrationServiceImpl();
        rideLists.forEach(filtrationService::sanitize);
        rideLists.forEach(e -> summary.put(e.get(0).getRideId(), summary.get(e.get(0).getRideId()) + "," + e.size()));

        // Logging summary
        for (Map.Entry<Long, String> entry : summary.entrySet()) {
            String[] strings = entry.getValue().split(",");
            logger.info("RideId: {}, Initial Size: {}, Sanitized Size: {}", entry.getKey(), Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        }

        CalculationService calculationService = new CalculationServiceImpl();
        List<RideFare> rideFares = rideLists.stream().map(calculationService::calculate).collect(Collectors.toList());
        rideFares.forEach(logger::info);

        String outputFilePath = filePath+ ".output";
        ingestionService.write(outputFilePath, rideFares);
    }
}