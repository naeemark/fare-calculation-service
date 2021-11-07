package org.naeemrk.fcs.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Naeem <naeemark@gmail.com>
 */
class TimeUtilTest {

    @Test
    @DisplayName("isPeakTime: Yes")
    void isPeekTime_true() {
        boolean peekTime = TimeUtil.isPeekTime(1636226859);
        assertTrue(peekTime);
    }

    @Test
    @DisplayName("isPeakTime: No")
    void isPeekTime_false() {
        boolean peekTime = TimeUtil.isPeekTime(1636286859);
        assertFalse(peekTime);
    }
}