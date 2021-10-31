package org.naeemrk.fcs.services.ingestion;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class IngestionServiceImpl implements IngestionService {
    @Override
    public void read(String fileName) {
        System.out.println(fileName);
    }
}
