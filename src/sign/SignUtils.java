/*
 * COPYRIGHT. ShenZhen JiMi Technology Co., Ltd. 2017.
 * ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
 * on any form or by any means, electronic, mechanical, photocopying, recording, 
 * or otherwise, without the prior written permission of ShenZhen JiMi Network Technology Co., Ltd.
 *
 * Amendment History:
 * 
 * Date                   By              Description
 * -------------------    -----------     -------------------------------------------
 * 2017年4月5日    yaojianping         Create the class
 * http://www.jimilab.com/
 */

package sign;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.taobao.api.Constants;
import com.taobao.api.internal.util.StringUtils;


public class SignUtils {
	
	public static String getTimeStamp()
    {
        //时间戳，格式为yyyy-MM-dd HH:mm:ss，时区为GMT+8，例如：2015-01-01 12:00:00。
        //淘宝API服务端允许客户端请求最大时间误差为10分钟。
        long curTime = new Date().getTime();
        //定义日期的中文输出格式,并输出日期 
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return df.format(curTime).toString();
    }

	public  String signTopRequest(Map<String, String> params) throws IOException {
		// 第一步：检查参数是否已经排序
		String[] keys = params.keySet().toArray(new String[0]);
		Arrays.sort(keys);

		// 第二步：把所有参数名和参数值串在一起
		StringBuilder query = new StringBuilder();
		if (Constants.SIGN_METHOD_MD5.equals("md5")) {
			query.append("695c1a459c1911e7bedb00219b9a2ef3");
		}
		for (String key : keys) {
			String value = params.get(key);
			if (StringUtils.areNotEmpty(key, value)) {
				query.append(key).append(value);
			}
		}

		// 第三步：使用MD5/HMAC加密
		byte[] bytes;
		if (Constants.SIGN_METHOD_HMAC.equals("md5")) {
			bytes = encryptHMAC(query.toString(), "695c1a459c1911e7bedb00219b9a2ef3");
		} else {
			query.append("695c1a459c1911e7bedb00219b9a2ef3");
			bytes = encryptMD5(query.toString());
		}

		// 第四步：把二进制转化为大写的十六进制
		return byte2hex(bytes);
	}

	public static byte[] encryptHMAC(String data, String secret) throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse.toString());
		}
		return bytes;
	}

	public static byte[] encryptMD5(String data) throws IOException {
		return encryptMD5(data.getBytes(Constants.CHARSET_UTF8));
	}

	private static byte[] encryptMD5(byte[] bytes) throws IOException {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IOException(e.toString());
		}
		return md.digest(bytes);
	}

	public static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

}
