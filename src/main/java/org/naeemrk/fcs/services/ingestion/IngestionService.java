package org.naeemrk.fcs.services.ingestion;

import org.naeemrk.fcs.common.Service;
import org.naeemrk.fcs.models.Location;

import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public interface IngestionService extends Service {
    List<Location> read(String filePath);

    List<List<Location>> split(List<Location> locationList);
}
