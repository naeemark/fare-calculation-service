package org.naeemrk.fcs.services.filtration;

import org.naeemrk.fcs.common.Service;
import org.naeemrk.fcs.models.Location;

import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public interface FiltrationService extends Service {
    void sanitize(List<Location> locationList);

    void setCurrentSpeed(Location currentLocation, Location lastLocation);
}
