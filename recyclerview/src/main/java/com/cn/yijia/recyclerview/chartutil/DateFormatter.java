package com.cn.yijia.recyclerview.chartutil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author lxm
 * @version 2020/6/12-13:57
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class DateFormatter {
	/**
	 * 日期格式转换 date_to_string
	 *
	 * @param date 日期
	 * @return 返回转换后的日期
	 */
	public static String getFormattedDateString(Date date) {

		try {
			SimpleDateFormat spf = new SimpleDateFormat( "EEE MMM d HH:mm:ss zzz yyyy", Locale.CHINA );
			String dateString = spf.format( date );

			Date newDate = spf.parse( dateString );
			spf = new SimpleDateFormat( "yyyy-MM-dd (HH:mm)", Locale.CHINA );
			if (newDate != null) {
				return spf.format( newDate );
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取只有月日时分的时间
	 *
	 * @param date 日期
	 * @return 更短的日期
	 */
	public static String getShortFormattedDateString(Date date) {

		try {
			SimpleDateFormat spf = new SimpleDateFormat( "EEE MMM d HH:mm:ss zzz yyyy", Locale.CHINA );
			String dateString = spf.format( date );

			Date newDate = spf.parse( dateString );
			spf = new SimpleDateFormat( "MM-dd (HH:mm)", Locale.CHINA );
			if (newDate != null) {
				return spf.format( newDate );
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取只有月日时分的时间
	 *
	 * @return 更短的日期
	 */
	public static Date getShortFormattedStringToDate(String time) {

		try {
//			SimpleDateFormat spf = new SimpleDateFormat( "EEE MMM d HH:mm:ss zzz yyyy", Locale.CHINA );
//			String dateString = spf.format( date );
//
//			Date newDate = spf.parse( dateString );
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss", Locale.CHINA );
//			if (newDate != null) {
//				return spf.format( newDate );
//			}
			Date parse = format.parse( time );
			return parse;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取只有月日时分的时间
	 *
	 * @param date 日期
	 * @return 更短的日期
	 */
	public static String getShortMDFormattedDateString(Date date) {

		try {
			SimpleDateFormat spf = new SimpleDateFormat( "EEE MMM d HH:mm:ss zzz yyyy", Locale.CHINA );
			String dateString = spf.format( date );

			Date newDate = spf.parse( dateString );
			spf = new SimpleDateFormat( "MM-dd", Locale.CHINA );
			if (newDate != null) {
				return spf.format( newDate );
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
