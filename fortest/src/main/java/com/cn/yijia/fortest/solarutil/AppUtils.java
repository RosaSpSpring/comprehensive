package com.cn.yijia.fortest.solarutil;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Base64;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * 日期格式转换 密码加密 键盘隐藏 打印toast
 */
public class AppUtils {



	/**
	 * @return 获取当前时间 日期
	 */
	public static Date getCurrentDateTime() {

		return Calendar.getInstance().getTime();
	}

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

	/**
	 * 获取只有月日的日期
	 * @param date 日期
	 * @return 日期
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
	 * 密码加密
	 *
	 * @param password 原始密码
	 * @return SHA-512 加密后的密码
	 */
	public static String generateHash(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance( "SHA-512" );
			md.update( password.getBytes() );
			byte[] byteData = md.digest();
			return Base64.encodeToString( byteData, Base64.NO_WRAP );

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 打印工具类
	 *
	 * @param context 上下文
	 * @param message toast 信息
	 */
	public static void showMessage(Context context, String message) {
		Toast.makeText( context, message, Toast.LENGTH_LONG ).show();
	}

	public static void openKeyboard(final Context context) {
		new Handler().postDelayed( new Runnable() {
			@Override
			public void run() {
				InputMethodManager imm = (InputMethodManager) context.getSystemService( Context.INPUT_METHOD_SERVICE );
				if (imm != null) {
					imm.toggleSoftInput( InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY );
				}
			}
		}, 500 );
	}

	/**
	 * 隐藏手机键盘
	 *
	 * @param activity 当前界面
	 */
	public static void hideKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService( Activity.INPUT_METHOD_SERVICE );
		if (inputMethodManager != null) {
			inputMethodManager.hideSoftInputFromWindow( Objects.requireNonNull( activity.getCurrentFocus() ).getWindowToken(), 0 );
		}
	}

}
