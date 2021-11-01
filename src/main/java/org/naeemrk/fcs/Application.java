package org.naeemrk.fcs;

import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.services.filteration.FiltrationService;
import org.naeemrk.fcs.services.filteration.FiltrationServiceImpl;
import org.naeemrk.fcs.services.ingestion.IngestionService;
import org.naeemrk.fcs.services.ingestion.IngestionServiceImpl;

import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class Application {

    public static void main(String[] args) {

        IngestionService ingestionService = new IngestionServiceImpl();
        List<Location> locationList = ingestionService.read("src/main/resources/data/paths_.csv");
        System.out.printf("Size: %d %n", locationList.size());

        FiltrationService filtrationService = new FiltrationServiceImpl();
        List<Location> sanitizedList = filtrationService.sanitize(locationList);
    }
}