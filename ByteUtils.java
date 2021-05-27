package com.company.httprealize;

/**
 * 字节数字互相转化工具类
 */
public class ByteUtils {
    //将 byte 数组 转化为 int 数字
    public static int byte2Int(byte[] bytes){
        int num = bytes[3] & 0xFF;
        num |= ((bytes[2] << 8) & 0xFF00);
        num |= ((bytes[1] << 16) & 0xFF000);
        num |= ((bytes[0] << 24) & 0xFF00000);
        return num;
    }
    //将 int 类型数字转化为 byte 数组
    public static byte[] Int2ByteArray(int i){
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);//强制转换为 byte 类型
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }
}
