package cn.emay.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 *
 * @author Frank
 */
public class DateUtils {

    /**
     * 获取两个日期之间的所有月份
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 所有月份[yyyyMM]
     */
    public static List<String> findMonthBetweenDate(Date beginTime, Date endTime) {
        if (beginTime == null) {
            throw new NullPointerException("beginTime is null");
        }
        if (endTime == null) {
            throw new NullPointerException("endTime is null");
        }
        List<String> retList = new ArrayList<>();
        LocalDate begin = beginTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().with(TemporalAdjusters.firstDayOfMonth());
        if (begin.isAfter(end)) {
            return retList;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");
        while (!begin.isAfter(end)) {
            retList.add(begin.format(dateTimeFormatter));
            begin = begin.plusMonths(1);
        }
        return retList;
    }

    /**
     * 把字符串转成日期
     *
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return 日期
     */
    public static Date parseDate(String dateStr, String pattern) {
        if (dateStr == null) {
            throw new NullPointerException("dateStr is null");
        }
        if (pattern == null) {
            throw new NullPointerException("format is null");
        }
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern(pattern.trim()))
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        LocalDateTime ldt = LocalDateTime.parse(dateStr.trim(), dateTimeFormatter);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 把日期转成字符串
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String toString(Date date, String pattern) {
        if (date == null) {
            throw new NullPointerException("date is null");
        }
        if (pattern == null) {
            throw new NullPointerException("format is null");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern.trim());
        LocalDateTime ldt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return ldt.format(dateTimeFormatter);
    }

    /**
     * 得到几天前的时间
     *
     * @param date 时间
     * @param days 天数
     * @return 时间
     */
    public static Date getDateBefore(Date date, int days) {
        return getDateAfter(date, -days);
    }

    /**
     * 得到几天后的时间
     *
     * @param date 时间
     * @param days 天数
     * @return 时间
     */
    public static Date getDateAfter(Date date, int days) {
        if (date == null) {
            throw new NullPointerException("date is null");
        }
        return Date.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(days).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取日期当天的最大时间日期【23:59:59 999】
     *
     * @param date 日期
     */
    public static Date getMaxTimeDateByDate(Date date) {
        if (date == null) {
            throw new NullPointerException("date is null");
        }
        LocalDateTime ldt = LocalDateTime.of(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalTime.MAX);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取日期当天的最小时间日期【00:00:00 000】
     *
     * @param date 日期
     */
    public static Date getMinTimeDateByDate(Date date) {
        if (date == null) {
            throw new NullPointerException("date is null");
        }
        LocalDateTime ldt = LocalDateTime.of(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalTime.MIN);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取两个时间的时间差<br/>
     * [data1-data2]
     *
     * @param data1 第一个时间
     * @param data2 第二个时间
     * @return 差值
     */
    public static long subTime(Date data1, Date data2) {
        return data1.getTime() - data2.getTime();
    }

    /**
     * 获取上月第一天最早时间
     *
     * @return Date
     */
    public static Date getLastMonthFirstDay() {
        return getLastMonthFirstDayByDate(new Date());
    }


    /**
     * 获取某日期的上月的最早时间
     *
     * @return Date
     */
    public static Date getLastMonthFirstDayByDate(Date date) {
        if (date == null) {
            throw new NullPointerException("date is null");
        }
        LocalDateTime ldt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusMonths(-1);
        LocalDate firstDay = ldt.toLocalDate().with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime newLdt = LocalDateTime.of(firstDay, LocalTime.MIN);
        return Date.from(newLdt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取上月最后一天最晚时间
     *
     * @return Date
     */
    public static Date getLastMonthLastDay() {
        return getLastMonthLastDayByDate(new Date());
    }

    /**
     * 获取某日期的上月的最晚时间
     *
     * @return Date
     */
    public static Date getLastMonthLastDayByDate(Date date) {
        if (date == null) {
            throw new NullPointerException("date is null");
        }
        LocalDateTime ldt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusMonths(-1);
        LocalDate lastDay = ldt.toLocalDate().with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime newLdt = LocalDateTime.of(lastDay, LocalTime.MAX);
        return Date.from(newLdt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取本月第一天最早时间
     *
     * @return Date
     */
    public static Date getNowMonthFirstDay() {
        return getTheMonthFirstDay(new Date());
    }

    /**
     * 获取日期当月最早时间
     *
     * @return Date 日期
     */
    public static Date getTheMonthFirstDay(Date date) {
        if (date == null) {
            throw new NullPointerException("date is null");
        }
        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().with(TemporalAdjusters.firstDayOfMonth());
        return Date.from(LocalDateTime.of(ld, LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取本月最后一天最晚时间
     *
     * @return Date
     */
    public static Date getNowMonthLastDay() {
        return getTheMonthLastDay(new Date());
    }

    /**
     * 获取日期当月最晚时间
     *
     * @return Date 日期
     */
    public static Date getTheMonthLastDay(Date date) {
        if (date == null) {
            throw new NullPointerException("date is null");
        }
        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().with(TemporalAdjusters.lastDayOfMonth());
        return Date.from(LocalDateTime.of(ld, LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
    }


}
