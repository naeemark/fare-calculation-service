package org.naeemrk.fcs.services.ingestion;

import org.naeemrk.fcs.common.Service;
import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.models.RideFare;

import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public interface IngestionService extends Service {
    List<Location> read(String filePath);

    void write(String filePath, List<RideFare> rideFares);

    List<List<Location>> split(List<Location> locationList);
}
