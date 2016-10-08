package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.ctc.wstx.util.DataUtil;

public class DateUtil {
	private static SimpleDateFormat LONG_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat FOLDER_FORMAT = new SimpleDateFormat(
			"yyyyMMdd");
	private static SimpleDateFormat LONG_FOLDER_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	private static SimpleDateFormat MID_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	private static SimpleDateFormat SHORT_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static long THREE_DAY_MILLSEC = 0xf731400L;
	public static long ONE_DAY_MILLSEC = 0x5265c00L;
	public static long ONE_HOUR_MILLSEC = 0x36ee80L;
	public static long THREE_HOURS_MILLSEC = 0xa4cb80L;
	public static long TWELVE_HOURS_MILLSEC = 0x2932e00L;

	public static String getLongCurrentDate() {
		return new String(LONG_FORMAT.format(new Date()));
	}

	public static String getLongZeroDate() {
		return new String(LONG_FORMAT.format(new Date(0L)));
	}

	public static String getLongDate(Date date) {
		if (date == null)
			return getLongZeroDate();
		else
			return new String(LONG_FORMAT.format(date));
	}

	public static String getLongDate(long value) {
		return new String(LONG_FORMAT.format(new Date(value)));
	}

	public static String getLongFolderZeroDate() {
		return new String(LONG_FOLDER_FORMAT.format(new Date(0L)));
	}

	public static String getLongFolerDate(Date date) {
		if (date == null)
			return getLongFolderZeroDate();
		else
			return new String(LONG_FOLDER_FORMAT.format(date));
	}

	public static String getFolerDate(long value) {
		return new String(FOLDER_FORMAT.format(new Date(value)));
	}

	public static String getFolerDate(Date date) {
		return new String(FOLDER_FORMAT.format(date));
	}

	public static String getShortCurrentDate() {
		return new String(SHORT_FORMAT.format(new Date()));
	}

	public static String getShortDate(Date date) {
		if (date == null)
			return getShortCurrentDate();
		else
			return new String(SHORT_FORMAT.format(date));
	}

	public static String getShortDate(long value) {
		return new String(SHORT_FORMAT.format(new Date(value)));
	}

	public static String getMidCurrentDate() {
		return new String(MID_FORMAT.format(new Date()));
	}

	public static String getMidDate(Date date) {
		if (date == null)
			return getMidCurrentDate();
		else
			return new String(MID_FORMAT.format(date));
	}

	public static String getMidDate(long value) {
		return new String(MID_FORMAT.format(new Date(value)));
	}

	public static Date parseLongDate(String timeText) {
		try {
			if (timeText != null)
				return LONG_FORMAT.parse(timeText);
		} catch (ParseException parseexception) {
			return null;
		}
		return null;
	}


	public static Date parseShortDate(String timeText) {
		try {
			if (timeText != null)
				return SHORT_FORMAT.parse(timeText);
		} catch (ParseException parseexception) {
			return null;
		}
		return null;
	}

	public static Date parseFolderDate(String timeText) {
		try {
			if (timeText != null)
				return FOLDER_FORMAT.parse(timeText);
		} catch (ParseException parseexception) {
			return null;
		}
		return null;
	}

	public static Date parseDate(long l) {
		Date dt = new Date();
		dt.setTime(l);
		return dt;
	}

	public static Date parseCustomDate(String formatStr, String timeText) {
		try {
			SimpleDateFormat formate = new SimpleDateFormat(formatStr);
			if (timeText != null)
				return formate.parse(timeText);
		} catch (ParseException parseexception) {
			return null;
		}
		return null;
	}

	public static String getCustomDate(String formatStr, long time) {
		SimpleDateFormat formate = new SimpleDateFormat(formatStr);
		return new String(formate.format(new Date(time)));
	}

	public static long getDayStartTimeByShortDate(String shortDateStr) {
		long time = 0L;
		Date date = parseShortDate(shortDateStr);
		if (date != null)
			time = date.getTime();
		return time;
	}

	public static long getDayEndTimeByShortDate(String shortDateStr) {
		long time = 0L;
		Date date = parseShortDate(shortDateStr);
		if (date != null)
			time = date.getTime() + 0x5265c00L;
		return time;
	}

	public static long getStartTimeByLongDate(String longDateStr) {
		long time = 0L;
		Date date = parseLongDate(longDateStr);
		if (date != null)
			time = date.getTime();
		return time;
	}

	public static long getEndTimeByLongDate(String longDateStr) {
		long time = System.currentTimeMillis();
		Date date = parseLongDate(longDateStr);
		if (date != null)
			time = date.getTime();
		return time;
	}

	/**
	 * 获取当天 00：00：00
	 * 
	 * @return
	 */
	public static String getTodayBeginTime() {
		Calendar currentDate = new GregorianCalendar();

		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		Date date = currentDate.getTime();
		return new String(LONG_FORMAT.format(date));
	}

	public static String getTodayBeginTimeByshort() {
		Calendar currentDate = new GregorianCalendar();

		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		Date date = currentDate.getTime();
		return new String(FOLDER_FORMAT.format(date));
	}

	public static String getYesTodayBeginTime() {
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		Date date = currentDate.getTime();

		Calendar yesTodayDate = Calendar.getInstance();
		yesTodayDate.setTime(date);
		yesTodayDate.add(yesTodayDate.DAY_OF_MONTH, -1);

		Date yesToday = yesTodayDate.getTime();
		return new String(LONG_FORMAT.format(yesToday));
	}

	/**
	 * 获取当天 23：59：59
	 * 
	 * @return
	 */
	public static String getTodayEndTime() {
		Calendar currentDate = new GregorianCalendar();

		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		Date date = currentDate.getTime();
		return new String(LONG_FORMAT.format(date));
	}

	public static String getYesTodayEndTime() {
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		Date date = currentDate.getTime();

		Calendar yesTodayDate = Calendar.getInstance();
		yesTodayDate.setTime(date);
		yesTodayDate.add(yesTodayDate.DAY_OF_MONTH, -1);

		Date yesToday = yesTodayDate.getTime();
		return new String(LONG_FORMAT.format(yesToday));
	}

	public static String getTodayEndTimeByshort() {
		Calendar currentDate = new GregorianCalendar();

		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		Date date = currentDate.getTime();
		return new String(LONG_FORMAT.format(date));
	}

	public static void main(String args[]) {

		System.out.println(DateUtil.getYesTodayBeginTime());
		System.out.println(DateUtil.getYesTodayEndTime());
	}
}