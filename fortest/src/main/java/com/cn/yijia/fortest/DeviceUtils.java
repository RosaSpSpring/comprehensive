package com.cn.yijia.fortest;


public class DeviceUtils {
    private static final String TAG = DeviceUtils.class.getSimpleName();

    public static boolean verifyCRC(String data, int expectLength, int crcLength) {
        if (data == null || data.length() != expectLength) {
            return false;
        }

        String JYM = CRC.calcCrc16(data.substring(2, expectLength - crcLength));
        return data.substring(expectLength - crcLength).equals(JYM);
    }
    public static boolean verifyCRC2(String data, int expectLength, int crcLength) {
        if (data == null || data.length() != expectLength) {
            return false;
        }
        String JYM = CRC2.getCRC( data.substring(0, expectLength - crcLength) ).replace( " ","" );
        System.out.println( "JYM = " + JYM );
        return data.substring(expectLength - crcLength ,expectLength).equals(JYM);
    }
}
