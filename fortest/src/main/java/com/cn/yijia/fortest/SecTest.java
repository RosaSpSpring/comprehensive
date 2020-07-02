package com.cn.yijia.fortest;

import android.util.Log;

import com.cn.yijia.fortest.solarutil.AppUtils;
import com.cn.yijia.fortest.solarutil.SolarTermsUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.cn.yijia.fortest.HandsCustomView.TAG;

/**
 * @author lxm
 * @version 2020/5/14-8:41
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class SecTest {
	public static void main(String[] args) {
		/**
		 * 测试 java 计算 float double 类型出现精度丢失
		float a = 0.9999999f;
		float b = 1;
		BigDecimal bigDecimal2 = new BigDecimal( a );
		System.out.println(bigDecimal2);
		BigDecimal bigDecimal = new BigDecimal(  Float.toString( a )  );
		System.out.println( "bigDecimal.floatValue() = " + bigDecimal);
		BigDecimal bigDecimal1 = new BigDecimal(  Float.toString( b )  );
		System.out.println( "bigDecimal1.floatValue() = " + bigDecimal1.floatValue() );
		//		System.out.println( "b - a = " + ( b - a ));
		BigDecimal subtract = bigDecimal1.subtract( bigDecimal );
		System.out.println( "subtract.floatValue() = " + subtract.floatValue() );
		float v = subtract.floatValue();
		System.out.println( "v = " + v );
		//		Log.e(TAG, "v" + String.valueOf( v ));
		if (v == 0.01f) {
			System.out.println("b - a == 0.01");
        } else {
			System.out.println("a - b != 0.01");
        }






		*//*HashMap<String, String> map = new HashMap<>();
		map.put( "1","11" );
		map.put( "2","22" );
		map.put( "3","33" );
		//键和值
		String key = null;
		String value = null;
		//*//*
		List<Integer> position_to_select = new ArrayList<>(  );
		for (int i = 0; i < 3; i++) {
			position_to_select.add( 1 );
			position_to_select.add( 2 );
			position_to_select.add( 2 );
		}

		// 循环判断选了几个

		boolean haveone = false;
		boolean havetwo = false;
		boolean havethree = false;
		for (int i = 0; i < position_to_select.size(); i++) {
			if (position_to_select.get( i ).equals( 1 )) {
				haveone = true;
			} else if (position_to_select.get( i ).equals( 2 )) {
				havetwo = true;
			} else if (position_to_select.get( i ).equals( 3 )) {
				havethree = true;
			}
			System.out.println( havethree );
		}
		// 三个都选择了
		if (haveone) {
			if (havetwo){
				if (havethree){
					System.out.println("三个全选了3");
				}else {
					System.out.println("第三个没有选择2");
				}
			}else {
				if (havethree){
					System.out.println("第二个没有选择第三个选择了2");
				}else {
					System.out.println("第二个没有选择，第三个也没有选择1");
				}
			}
		} else {
			if(havetwo){
				if (havethree){
					System.out.println("第一个没有选择，第二个选择，第三个选择 2");
				}else {
					System.out.println("第一个没有选择，第二个选择，第三个没选 1");
				}
			}else {
				if (havethree){
					System.out.println("第一，二都没有选，第三个选择 1");
				}else {
					System.out.println("第一，二，三都没有选择 0");
				}
			}

		}*/




	}

	private String inittomorrow() {
		Date date = new Date();

		//		Log.e( TAG, "solarTerm" + solarTerm );
		long time = date.getTime();
		long oneday = 24 * 3600 * 1000;
		long tomorrow = time + oneday;
		Date date1 = new Date( tomorrow );

		return SolarTermsUtil.getSolarTerm( date1 );
	}

	// 获取当前节气日期
	private  String initSolar() {

		// 获得当前日期节气
		Date date = new Date();

		//		Log.e( TAG, "solarTerm" + solarTerm );
		long time = date.getTime();
		long oneday = 24 * 3600 * 1000;
		long addoneday = time + oneday;
		long subtractday = time - oneday;

		String solarTerm = SolarTermsUtil.getSolarTerm( date );
		ArrayList<Date> dates = new ArrayList<>();
		dates.add( date );

		while (true) {

			Date date1 = new Date( addoneday );
			String solarTerm1 = SolarTermsUtil.getSolarTerm( date1 );

			if (solarTerm.equals( solarTerm1 )) {
				dates.add( date1 );
				addoneday += oneday;
			} else {
				break;
			}
		}
		while (true) {
//			Log.e( TAG, "jinru" );

			Date date1 = new Date( subtractday );
			String solarTerm1 = SolarTermsUtil.getSolarTerm( date1 );

			if (solarTerm.equals( solarTerm1 )) {
				dates.add( date1 );
				subtractday -= oneday;
				System.out.println( "solarTerm1 = " + solarTerm1 );
			} else {
				break;
			}

		}


		Collections.sort( dates );
		/*for (int i = 0; i < dates.size(); i++) {
			System.out.println( "dates = " + dates.get( i ) );
		}*/
		Date date1 = dates.get( 0 );
		Date date2 = dates.get( dates.size() - 1 );
		StringBuilder stringBuilder = new StringBuilder();
		String s1 = AppUtils.getShortMDFormattedDateString( date1 );
		String s2 = AppUtils.getShortMDFormattedDateString( date2 );


		StringBuilder formatdate = formatdate( stringBuilder, s1 );
		String solartoshow = formatdate.toString() + "-" ;
		StringBuilder formatdate1 = formatdate( stringBuilder, s2 );
		solartoshow = solartoshow + formatdate1.toString();
		return solartoshow;

	}
	// 将 日期中的 0 位去掉 例如 05月03日 转换 5月3日
	private StringBuilder formatdate(StringBuilder stringBuilder, String s) {
		if (stringBuilder.length() != 0)
			stringBuilder.delete( 0, stringBuilder.length() );
		stringBuilder.append( String.format( Locale.CHINA, "%2d", Integer.parseInt( Objects.requireNonNull( s ).substring( 0, 2 ) ) ).trim() ).append( "月" );
		stringBuilder.append( String.format( Locale.CHINA, "%2d", Integer.parseInt( s.substring( 3, 5 ) ) ).trim() ).append( "日" );
//		Log.e( TAG, "stringBu" + stringBuilder );
		return stringBuilder;
	}
}
