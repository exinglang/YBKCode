package com.puxtech.ybk.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * aes加密
 * 
 * @author fanshuo
 * @version 1.0
 */
public class AES {
	/**
	 * 加密
	 */
	public static byte[] encrypt(byte[] byteContent, byte[] password)
			throws Exception {
		SecretKeySpec key = new SecretKeySpec(password, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(byteContent);
		return result; // 加密
	}

	/**
	 * 解密
	 */
	public static byte[] decrypt(byte[] content, byte[] password)
			throws Exception {
		SecretKeySpec key = new SecretKeySpec(password, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(content);
		return result; // 加密
	}
	
}