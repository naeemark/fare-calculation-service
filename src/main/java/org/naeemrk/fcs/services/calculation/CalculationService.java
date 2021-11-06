package org.naeemrk.fcs.services.calculation;

import org.naeemrk.fcs.common.Service;
import org.naeemrk.fcs.models.Location;
import org.naeemrk.fcs.models.RideFare;

import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public interface CalculationService extends Service {
    RideFare calculate(List<Location> locationList);
}
