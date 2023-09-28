package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtils {

    private static final String YEAR_MONTH_DAY_SPACE_HOUR_MIN_SEC = "yyyy-MM-dd HH:mm:ss";
    private static final String YEAR_MONTH = "yyyy-MM";

    /**
     * Return the date of today with specific formatter, for example yyyy-MM-dd HH:mm:ss means 2022-10-01 09:00:00
     * @param formatter any valid date formatter
     * @return the date of today
     */
    public static String getTodayWithFormatter(String formatter) {
        return new SimpleDateFormat(formatter).format(new Date());
    }

    /**
     * Return the date of tomorrow with specific formatter, for example yyyy-MM-dd HH:mm:ss means 2022-10-01 09:00:00
     * @param formatter any valid date formatter
     * @return the date of tomorrow
     */
    public static String getTomorrowWithFormatter(String formatter) {
        Calendar CALENDAR = Calendar.getInstance();
        CALENDAR.setTime(new Date());
        CALENDAR.add(Calendar.DAY_OF_MONTH, 1);
        return new SimpleDateFormat(formatter).format(CALENDAR.getTime());
    }

    /**
     * Return the date of yesterday with specific formatter, for example yyyy-MM-dd HH:mm:ss means 2022-10-01 09:00:00
     * @param formatter any valid date formatter
     * @return the date of yesterday
     */
    public static String getYesterdayWithFormatter(String formatter) {
        Calendar CALENDAR = Calendar.getInstance();
        CALENDAR.setTime(new Date());
        CALENDAR.add(Calendar.DAY_OF_MONTH, -1);
        return new SimpleDateFormat(formatter).format(CALENDAR.getTime());
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getLocalFormatTime() {
        return getTodayWithFormatter(YEAR_MONTH_DAY_SPACE_HOUR_MIN_SEC);
    }

    /**
     *
     * @param date
     * @param format
     * @return
     */
    public static String getTimeWithFormat(Date date, String format) {
        Date date1 = Optional.ofNullable(date).orElse(new Date());
        return new SimpleDateFormat(format).format(date1);
    }

    public static String getTomorrowTimeWithFormat(Date date, String format) {
        Date date1 = Optional.ofNullable(date).orElse(new Date());
        long newTime = date1.getTime() + 1000 * 60 * 60 * 24;
        date1.setTime(newTime);
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date1);
    }

    public static String getYesterdayTimeWithFormat(Date date, String format) {
        Date date1 = Optional.ofNullable(date).orElse(new Date());
        long newTime = date1.getTime() - 1000 * 60 * 60 * 24;
        date1.setTime(newTime);
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date1);
    }

    public static String getDayAfterTomorrowTimeWithFormat(Date date, String format) {
        Date date1 = Optional.ofNullable(date).orElse(new Date());
        long newTime = date1.getTime() + 1000 * 60 * 60 * 24 * 2;
        date1.setTime(newTime);
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date1);
    }

    public static String getYearMonthDay() {
        return getTimeWithFormat(new Date(), "yyyy-MM-dd");
    }

}
