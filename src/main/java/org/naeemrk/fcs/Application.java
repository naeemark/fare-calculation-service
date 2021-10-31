package org.naeemrk.fcs;

import org.naeemrk.fcs.services.ingestion.IngestionService;
import org.naeemrk.fcs.services.ingestion.IngestionServiceImpl;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class Application {

    public static void main(String[] args) {
        System.out.println("Hello World...");

        IngestionService ingestionService = new IngestionServiceImpl();
        ingestionService.read("fileName");
    }
}