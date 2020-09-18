package cn.emay.utils.date;

import java.util.Date;

/**
 * @author Frank
 */
public class DateUtilsTest {

    public static void main(String[] args) {
        System.out.println("DateUtils.toString");
        System.out.println(DateUtils.toString(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println("DateUtils.getLastMonthFirstDay");
        System.out.println(DateUtils.toString(DateUtils.getLastMonthFirstDay(), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println("DateUtils.getLastMonthLastDay");
        System.out.println(DateUtils.toString(DateUtils.getLastMonthLastDay(), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println("DateUtils.getMaxTimeDateByDate(date)");
        System.out.println(DateUtils.toString(DateUtils.getMaxTimeDateByDate(new Date(System.currentTimeMillis() + 23 * 60 * 60 * 1000)), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println("DateUtils.getMinTimeDateByDate(date)");
        System.out.println(DateUtils.toString(DateUtils.getMinTimeDateByDate(new Date(System.currentTimeMillis() + 23 * 60 * 60 * 1000)), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println("DateUtils.getNowMonthFirstDay");
        System.out.println(DateUtils.toString(DateUtils.getNowMonthFirstDay(), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println("DateUtils.getNowMonthLastDay");
        System.out.println(DateUtils.toString(DateUtils.getNowMonthLastDay(), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println("DateUtils.getTheMonthLastDay(date)");
        System.out.println(DateUtils.toString(DateUtils.getTheMonthLastDay(new Date()), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println("DateUtils.parseDate");
        System.out.println(DateUtils.parseDate("2017-01-01 11:11:11 111", "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println("DateUtils.subTime");
        System.out.println(DateUtils.subTime(new Date(System.currentTimeMillis() + 60 * 1000), new Date()));
        System.out.println("DateUtils.getTheMonthFirstDay(date)");
        System.out.println(DateUtils.toString(DateUtils.getTheMonthFirstDay(new Date()), "yyyy-MM-dd HH:mm:ss SSS"));

    }

}
