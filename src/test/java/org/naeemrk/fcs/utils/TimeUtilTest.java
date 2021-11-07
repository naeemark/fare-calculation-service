package org.naeemrk.fcs.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Naeem <naeemark@gmail.com>
 */
class TimeUtilTest {

    private long midNight ;
    private long peakTimeEpoch;
    private long offPeakTimeEpoch;

    @BeforeEach
    void setUp() {
        ZoneId zone = ZoneOffset.systemDefault();
        ZonedDateTime zdt = ZonedDateTime.now(zone);
        zdt = zdt.toLocalDate().atStartOfDay(zone);
        midNight = zdt.toInstant().toEpochMilli() / 1000;
        peakTimeEpoch = midNight + 10000;
        offPeakTimeEpoch = peakTimeEpoch + 20000;
    }

    @Test
    @DisplayName("isPeakTime: Yes")
    void isPeekTime_true() {
        boolean peekTime = TimeUtil.isPeekTime(peakTimeEpoch);
        assertTrue(peekTime);
    }

    @Test
    @DisplayName("isPeakTime: No")
    void isPeekTime_false() {
        boolean peekTime = TimeUtil.isPeekTime(offPeakTimeEpoch);
        assertFalse(peekTime);
    }

    @Test
    @DisplayName("isPeakTime: No")
    void isPeekTime_false_b() {
        boolean peekTime = TimeUtil.isPeekTime(midNight);
        assertFalse(peekTime);
    }
}