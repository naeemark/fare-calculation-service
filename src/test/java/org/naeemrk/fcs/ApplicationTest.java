package org.naeemrk.fcs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This works as Mocked End-to-End test with sample data
 *
 * @author Naeem <naeemark@gmail.com>
 */
class ApplicationTest {

    private final String filePath = "src/test/resources/data/paths.csv";

    @Test
    @DisplayName("Main Method")
    void main() {
        String[] args = {filePath};
        Application.main(args);
        assertTrue(true); // stub assertion
    }

    @Test
    @DisplayName("Main Method Exception")
    void main_exceptions() {
        String[] args = {};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Application.main(args));
        assertNotNull(exception);
    }
}