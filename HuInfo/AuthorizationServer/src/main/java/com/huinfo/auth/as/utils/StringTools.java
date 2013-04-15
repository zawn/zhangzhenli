package com.huinfo.auth.as.utils;

/**
 * String工具类
 * 
 * @author ZhangZhenli
 */
public class StringTools {

	/**
	 * 十六进制的数字
	 */
	private static final String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	/**
	 * 字节转化为十六进制字符串。
	 * 
	 * @param b 字节
	 * @return 十六进制字符串
	 */
	private static String byteToHexString(byte b) {
		return HEX_DIGITS[(b & 0xF0) >> 4] + HEX_DIGITS[b & 0xF];
	}

	/**
	 * 字节数组转化为十六进制字符串。
	 * 
	 * @param bytes 字节数组
	 * @return 十六进制字符串
	 */
	public static String byteArrayToHexStringPrint(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		char[] tempheader = new char[8];
		byte[] tempb = new byte[0x10];
		StringBuilder tempsb = new StringBuilder();
		int i = 0x00;
		sb.append(String.format("%1$#010x", i).toUpperCase());
		sb.append(" ; ");
		for (; i < bytes.length; i++) {
			byte b = bytes[i];
			tempb[i % 0x10] = b;
			sb.append(HEX_DIGITS[(b & 0xF0) >> 4]);
			sb.append(HEX_DIGITS[b & 0xF]);
			sb.append(" ");
			if (i % 0x10 == 0x0F) {
				sb.append(";");
				sb.append(new String(tempb));
				sb.append("\n");
				sb.append(String.format("%1$#010x", i + 1).toUpperCase());
				sb.append(" ; ");
			}
		}
		if (i % 0x10 != 0x0F) {
			for (int j = 0; j < 0x10 - i % 0x10; j++) {
				sb.append("   ");
				tempb[0x0F - j] = 20;
			}
			sb.append(";");
			sb.append(new String(tempb));
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * 字节数组转化为十六进制字符串。
	 * 
	 * @param bytes 字节数组
	 * @return 十六进制字符串
	 */
	public static String byteArrayToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		char[] tempheader = new char[8];
		byte[] tempb = new byte[0x10];
		StringBuilder tempsb = new StringBuilder();
		int i = 0x00;
		for (; i < bytes.length; i++) {
			byte b = bytes[i];
			tempb[i % 0x10] = b;
			sb.append(HEX_DIGITS[(b & 0xF0) >> 4]);
			sb.append(HEX_DIGITS[b & 0xF]);
			// if (i % 0x10 == 0x0F) {
			// sb.append("\n");
			// }
		}
		return sb.toString();
	}

	/**
	 * 字节数组转化为十六进制字符串。
	 * 
	 * @param bytes 字节数组
	 * @return 十六进制字符串
	 */
	public static byte[] hexStringToByteArray(String hexString) {
		final int length = hexString.length();
		if ((length & 0x01) != 0) {
			throw new RuntimeException("Odd number of characters.");
		}
		char[] temp = new char[length];
		hexString.getChars(0, hexString.length(), temp, 0);
		return decodeHex(temp);
	}

	/**
	 * 将十六进制字符数组转换为字节数组
	 * 
	 * @param data 十六进制char[]
	 * @return byte[]
	 * @throws RuntimeException 如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
	 */
	private static byte[] decodeHex(char[] data) {

		int len = data.length;
		if ((len & 0x01) != 0) {
			throw new RuntimeException("Odd number of characters.");
		}
		byte[] out = new byte[len >> 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; j < len; i++) {
			int f = Character.digit(data[j], 16) << 4;
			j++;
			f = f | Character.digit(data[j], 16);
			;
			j++;
			out[i] = (byte) (f & 0xFF);
		}
		return out;
	}
}
