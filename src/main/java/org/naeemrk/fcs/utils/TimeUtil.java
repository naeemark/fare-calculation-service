package org.naeemrk.fcs.utils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

import static org.naeemrk.fcs.common.Constants.DURATION_PEAK_TIME;

/**
 * @author Naeem <naeemark@gmail.com>
 */
public class TimeUtil {

    private TimeUtil() {
    }

    /**
     * Checks if the location time belongs to the Peak Hours
     *
     * @param epochTimestamp time
     * @return boolean
     */
    public static boolean isPeekTime(long epochTimestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(epochTimestamp * 1000);

        LocalTime start = LocalTime.parse(DURATION_PEAK_TIME[0]);
        LocalTime end = LocalTime.parse(DURATION_PEAK_TIME[1]);
        LocalTime target = LocalTime.parse(new SimpleDateFormat("HH:mm:ss").format(calendar.getTime()));

        return (target.isAfter(start) && target.isBefore(end));
    }
}
