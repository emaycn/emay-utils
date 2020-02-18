package cn.emay.utils.date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 * 
 * @author Frank
 *
 */
public class DateUtils {

	/**
	 * 获取两个日期之间的所有月份
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static List<String> findMonthBetweenDate(Date beginTime, Date endTime) {
		List<String> retList = new ArrayList<String>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		String endYm = sdf.format(endTime);

		Calendar start = Calendar.getInstance();
		start.setTime(beginTime);

		String startYm = null;
		while (!endYm.equals(startYm = sdf.format(start.getTime()))) {
			start.add(Calendar.MONTH, 1);
			retList.add(startYm);
		}
		retList.add(endYm);
		return retList;
	}

	/**
	 * 把日期转成字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(Date date, String format) {
		String dateStr = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateStr = sdf.format(date);
		} catch (Exception e) {
		}
		return dateStr;
	}

	/**
	 * 把字符串转成日期
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(dateStr);
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * 获取日期当天的最小时间日期【00:00:00 000】
	 */
	public static Date getMinTimeDateByDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cale.set(Calendar.MILLISECOND, 0);
		return cale.getTime();
	}

	/**
	 * 获取日期当天的最大时间日期【23:59:59 999】
	 */
	public static Date getMaxTimeDateByDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cale.set(Calendar.MILLISECOND, 999);
		return cale.getTime();
	}

	/**
	 * 获取两个时间的时间差<br/>
	 * [d1-d2]
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long subTime(Date d1, Date d2) {
		return d1.getTime() - d2.getTime();
	}

	/**
	 * 获取上月第一天最早时间
	 *
	 * @return Date
	 */
	public static Date getLastMonthFirstDay() {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		cal1.add(Calendar.MONTH, -1);
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		return cal1.getTime();
	}

	/**
	 * 获取上月最后一天最晚时间
	 *
	 * @return Date
	 */
	public static Date getLastMonthLastDay() {
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		cale.add(Calendar.MONTH, -1);
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cale.set(Calendar.MILLISECOND, 999);
		return cale.getTime();
	}

	/**
	 * 获取本月第一天最早时间
	 *
	 * @return Date
	 */
	public static Date getNowMonthFirstDay() {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		return cal1.getTime();
	}

	/**
	 * 获取本月最后一天最晚时间
	 *
	 * @return Date
	 */
	public static Date getNowMonthLastDay() {
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cale.set(Calendar.MILLISECOND, 999);
		return cale.getTime();
	}

	/**
	 * 获取本月第一天最早时间
	 *
	 * @return Date
	 */
	public static Date getTheMonthFirstDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cale.set(Calendar.MILLISECOND, 0);
		return cale.getTime();
	}

	/**
	 * 获取本月最后一天最晚时间
	 *
	 * @return Date
	 */
	public static Date getTheMonthLastDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cale.set(Calendar.MILLISECOND, 999);
		return cale.getTime();
	}

	/**
	 * 获取某日期上月第一天最早时间
	 *
	 * @return Date
	 */
	public static Date getLastMonthFirstDayByDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		cal1.add(Calendar.MONTH, -1);
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		return cal1.getTime();
	}

	/**
	 * 获取某日期上月最后一天最晚时间
	 *
	 * @return Date
	 */
	public static Date getLastMonthLastDayByDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.MONTH, -1);
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
		cale.set(Calendar.HOUR_OF_DAY, 23);
		cale.set(Calendar.MINUTE, 59);
		cale.set(Calendar.SECOND, 59);
		cale.set(Calendar.MILLISECOND, 999);
		return cale.getTime();
	}

	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

}
