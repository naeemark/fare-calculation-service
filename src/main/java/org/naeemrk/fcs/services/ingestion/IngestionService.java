package org.naeemrk.fcs.services.ingestion;

import org.naeemrk.fcs.common.Service;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public interface IngestionService extends Service {
    void read(String fileName);
}
