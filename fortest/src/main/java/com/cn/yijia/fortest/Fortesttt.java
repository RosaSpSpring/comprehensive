package com.cn.yijia.fortest;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * @author lxm
 * @version 2020/5/8-17:05
 * @des
 * @updateDes
 * @updateAuthor $
 */
public class Fortesttt {
	static byte[] INIT_DATA = new byte[1];
	private byte[] left = INIT_DATA;
	private byte[] right = INIT_DATA;



/*	public static void main(String[] args) {
		*//*String str = "681275081001006400640001CC7C16";

		String substring = str.substring( 2, str.length()-2 );

		boolean b = DeviceUtils.verifyCRC2( substring, 26, 4);
		System.out.println( "b = " + b );
*//*

		*//*String strs = "1275081001006400640001CC7C";
		String string = CRC.calcCrc16( strss);
		System.out.println( "string = " + string );*//*

		*//*byte b = Byte.parseByte( "23", 34);
		int i = Integer.parseInt( "12", 13 );
		System.out.println( "i = " + i );
		System.out.println(b);*//*
		//		intCopyDemo();
	*//*	String data = "00A6";
		String data1 = "0093";


		byte[] bytes = parsePointData( data );
		byte b = parsePointData2( data1 );
		System.out.println( "b" + b );

		System.out.println( bytes.length );
		int k = 0 ;
		System.out.println(k++);
		Fortesttt fortesttt =
				new Fortesttt();
		float[] dataAsFloatArray = fortesttt.getDataAsFloatArray();
		System.out.println( "dataAsFloatArray = " + dataAsFloatArray.length );
*//*
	// parsePointData3
		String data = "0082";
		byte[] bytes = parsePointData3( data );
		if (bytes != null){
			for (int i = 0; i < bytes.length; i++) {
				System.out.println("bytes" + bytes[i] + "length" + bytes.length );
			}
		}else {
			Log.e(TAG, "parsePointData is null"  );
		}


	}*/

	// byte[] to hex string. 先把byte转换成 int ，然后利用Integer.toHexString(int)来转换成16进制字符串。


	private static byte[] parsePointData3(String data) {
		byte[] d = new byte[1];
		//        d[0] =  Byte.parseByte(data.substring(0, 2), 16);
		//        d[1] =  Byte.parseByte(data.substring(2, 4), 16);
		//        d[2] = Byte.parseByte(data.substring(4, 6), 16);
		// 源数据会越界，不知道为什么，只是修改后的不会越界报错的，不清楚对不对

		//        d[0] = Integer.valueOf(data.substring(0, 2), 16).byteValue();

		d[0] = Integer.valueOf(data.substring(2, 4), 16).byteValue();
		int temp = d[0] >=0 ? d[0] : d[0] + 256;
		System.out.println( "temp = " + temp );


		System.out.println( "d[0] = " + d[0] );

		return d;
	}

	public static byte[] intToBytes(int value )
	{
		byte[] src = new byte[1];
		src[0] =  (byte) (value & 0xFF);
		return src;
	}

	public  float[] getDataAsFloatArray() {
		float[] arr = new float[left.length + right.length];
		int k = 0;
		for (int i = 0; i < left.length; i++) {
			arr[k++] = left[i];
		}
		for (int i = 0; i < right.length; i++) {
			arr[k++] = right[i];
		}
//		Log.e(TAG, "getDataAsFloatArray" + arr.length + "left.length" + left.length + "right.length" + right.length);
		return arr;
	}

	private static byte parsePointData2(String data) {
		return Integer.valueOf(data.substring(0, 4), 16).byteValue();
	}


	private static byte[] parsePointData(String data) {
		byte[] d = new byte[2];
//		d[0] = Byte.parseByte(data.substring(0, 2), 16);
		d[0] = Integer.valueOf(data.substring(0, 2), 16).byteValue();


//		d[1] = Byte.parseByte(data.substring(2, 4), 16);
		d[1] = Integer.valueOf(data.substring(2, 4), 16).byteValue();
		//        d[2] = Byte.parseByte(data.substring(4, 6), 16);
		System.out.println(d[0] + "+++++" + d[1]);
		double[] dd = new double[]{
				31.0,30.0,27.0,
				30.0,27.0,26.0,
				31.0,31.0,30.0,
				26.0,27.0,26.0,
				21.0,21.0,21.0,
				40.0,44.0,44.0,
				36.0,36.0,36.0,
				38.0,38.0,37.0,
				23.0,24.0,24.0,
				38.0,38.0,37.0,
				25.0,24.0,23.0,
				23.0,23.0,23.0,
				16.0,15.0,15.0,
				40.0,42.0,43.0,
				24.0,22.0,21.0,
				26.0,26.0,26.0,
				34.0,35.0,37.0,
				39.0,38.0,37.0,
				31.0,30.0,26.0,
				32.0,34.0,33.0};
		return d;
	}

	private static void intCopyDemo() {
		int[] mCopySrcInt = {1, 2, 3, 8, 6, 6, 8, 3, 2, 1};
		int[] mCopyDstInt = new int[mCopySrcInt.length];

		System.out.println( "Destination array before arraycopy" );
		for (int num : mCopyDstInt) {
			System.out.print( "" + num + ", " );
		}
		System.out.println();

		System.arraycopy( mCopySrcInt, 0, mCopyDstInt, 0, mCopySrcInt.length - 3 );

		System.out.println( "Destination array after arraycopy" );
		for (int num : mCopyDstInt) {
			System.out.print( "" + num + ", " );
		}
	}

}
