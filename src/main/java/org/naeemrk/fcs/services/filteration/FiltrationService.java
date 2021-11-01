package org.naeemrk.fcs.services.filteration;

import org.naeemrk.fcs.common.Service;
import org.naeemrk.fcs.models.Location;

import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public interface FiltrationService extends Service {
    boolean isValid(Location location);
    List<Location> sanitize(List<Location> locationList);
}
