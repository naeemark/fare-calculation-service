package org.naeemrk.fcs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Naeem <naeemark@gmail.com>
 */
class ApplicationTest {

    private final String filePath = "src/test/resources/data/paths.csv";

    @Test
    @DisplayName("Main Method")
    void main() {
        String[] args = {filePath};
//        Application.main(args);
    }
}