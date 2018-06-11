package cn.emay.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author Frank
 *
 */
public class DateUtils {

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
		Calendar cal_1 = Calendar.getInstance();
		cal_1.setTime(new Date());
		cal_1.add(Calendar.MONTH, -1);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);
		cal_1.set(Calendar.HOUR_OF_DAY, 0);
		cal_1.set(Calendar.MINUTE, 0);
		cal_1.set(Calendar.SECOND, 0);
		cal_1.set(Calendar.MILLISECOND, 0);
		return cal_1.getTime();
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
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cale.set(Calendar.MILLISECOND, 0);
		return cale.getTime();
	}

	/**
	 * 获取本月第一天最早时间
	 *
	 * @return Date
	 */
	public static Date getNowMonthFirstDay() {
		Calendar cal_1 = Calendar.getInstance();
		cal_1.setTime(new Date());
		cal_1.set(Calendar.DAY_OF_MONTH, 1);
		cal_1.set(Calendar.HOUR_OF_DAY, 0);
		cal_1.set(Calendar.MINUTE, 0);
		cal_1.set(Calendar.SECOND, 0);
		cal_1.set(Calendar.MILLISECOND, 0);
		return cal_1.getTime();
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
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cale.set(Calendar.MILLISECOND, 0);
		return cale.getTime();
	}

	/**
	 * 获取本月最后一天最早时间
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
		cale.set(Calendar.HOUR, 0);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		cale.set(Calendar.MILLISECOND, 0);
		return cale.getTime();
	}

}
