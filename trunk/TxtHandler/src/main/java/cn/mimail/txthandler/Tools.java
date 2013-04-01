/*
 * 文件名：Tools.java
 * 版权：Copyright 2003-2007 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：MMSC V100R002 Relay 系统用到的常用工具方法
 * 修改人：〈修改人〉
 * 修改时间：YYYY-MM-DD
 * 跟踪单号：〈跟踪单号〉
 * 修改单号：〈修改单号〉
 * 修改内容：〈修改内容〉
 */
package cn.mimail.txthandler;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * 该类用于保存本系统用到的常用工具方法。
 * 当系统中有常用、功能明确的方法均放置在本类中作为静态方法。
 *
 * @author 李大伟
 * @version 1.0
 */
public final class Tools
{
    /** 新TransactionID 的长度 */
    public static final int NEW_TID_LENGTH = 28;
    
    /** 老TransactionID 的长度 */
    public static final int OLD_TID_LENGTH = 27;
    
    private static final int FIX_NUMBER256 = 256;
    
    private static final int FIX_NUMBER16 = 16;
    
    private static final int FIX_NUMBER27 = 27;
    
    private static final int FIX_NUMBER3 = 3;
    
    private static final int FIX_NUMBER8 = 8;
    
    private static final int FIX_NUMBER1024 = 1024;
    
    private static final int FIX_NUMBER_0XFF = 0xff;
    
    /////////////////////////////////////////////////////////////////////////////
    //added by fangxiang59711 2007
    private static final int BLOCK_SIZE = 4 * 1024;
    
    private static final int STREAM_SIZE = 100 * 1024;
    
    //VERSION次版本号最大位数
    private static final int C_LEN = 2;
    
    //begin:Add by luyi CMCCD700 数字水印 2007-05-24
    //mod for NYGD14951 By lht16972 begin
    //VERSION次版本号位数(二进制形式)
    private static final int C_LEN_BIN = 4;
    
    //默认的VERSION版本号1.0
    private static final int DEFAULT_VERSION = 144;
    
    //数字15
    private static final int FIFTEEN = 15;
    
    /** 十六进制的数字 */
    private static final String[] HEX_DIGITS =
        {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    
    //字符串形式15
    private static final String FIFTEEN_STR = "15";
    
    //数字1
    private static final int ONE = 1;
    
    //数字7
    private static final int SEVEN = 7;
    
    //VERSION主版本号最大位数
    private static final int Z_LEN = 1;
    
    //VERSION主版本号位数(二进制形式)
    private static final int Z_LEN_BIN = 3;
    
    //数字0
    private static final int ZERO = 0;
    
    /**
     * 该类仅用来保存静态方法，不应实例化。
     */
    private Tools()
    {
    }
    
    /**
     * 字节数组转化为十六进制字符串。
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    public static String byteArrayToHexString(byte[] b)
    {
        String result;
        StringBuilder resultBak = new StringBuilder();
        for (int i = 0; i < b.length; i++)
        {
            resultBak.append(byteToHexString(b[i]));
        }
        result = resultBak.toString();
        return result;
    }
    
    /**
     * 字节转化为十六进制字符串。
     *
     * @param b 字节
     * @return 十六进制字符串
     */
    private static String byteToHexString(byte b)
    {
        int n = b;
        if (n < 0)
        {
            n = FIX_NUMBER256 + n;
        }
        int d1 = n / FIX_NUMBER16;
        int d2 = n % FIX_NUMBER16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }
    /**
     * 将字节数组格式化为十六进制字符串。
     *
     * @param data 字节数组
     * @return 十六进制字符串
     */
    public static String getHexString(byte[] data)
    {
        String str = "";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        str = stream.toString();
        return str;
    }
    
    /**
     * 将字节数组格式化为十六进制字符串。
     * @param data 字节数组
     * @param offset 开始位置
     * @param length 长度
     * @return  十六进制字符串
     * @see [类、类#方法、类#成员]
     */
    public static String getHexString(byte[] data, int offset, int length)
    {
        String str = "";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        str = stream.toString();
        return str;
    }
}
