package com.visionet.repair.common.utils;

import java.io.IOException;
import java.security.MessageDigest;

/**
 * 
 * @author 李晓健
 * @date 2013年12月9日 下午5:52:49
 */
public class Md5Util32 {
	public final static String md5(String plainText) {
		// 返回字符串
		String md5Str = null;
		try {
			// 操作字符串
			StringBuffer buf = new StringBuffer();
			/**
			 * MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
			 * 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
			 * 
			 * MessageDigest 对象开始被初始化。 该对象通过使用 update()方法处理数据。 任何时候都可以调用
			 * reset()方法重置摘要。 一旦所有需要更新的数据都已经被更新了，应该调用digest()方法之一完成哈希计算。
			 * 
			 * 对于给定数量的更新数据，digest 方法只能被调用一次。 在调用 digest 之后，MessageDigest
			 * 对象被重新设置成其初始状态。
			 */
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 添加要进行计算摘要的信息,使用 plainText 的 byte 数组更新摘要。
			md.update(plainText.getBytes());
			// 计算出摘要,完成哈希计算。
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				// 将整型 十进制 i 转换为16位，用十六进制参数表示的无符号整数值的字符串表示形式。
				buf.append(Integer.toHexString(i));
			}
			// 32位的加密
			md5Str = buf.toString();
			// 16位的加密
			// md5Str = buf.toString().md5Strstring(8,24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}
	public static void main(String[] args) throws IOException {
	//	System.out.println(md5("lxj41523638"));
//		HttpRequester request = new HttpRequester();
//		request.setDefaultContentEncoding("UTF-8");
		//取剩余短信条数
//		String url="http://http.yunsms.cn/mm/?uid=131294&pwd=509c39d3eb5b363e8f4be986e0355178";
		//取已发送总条数
//		String url="http://http.yunsms.cn/mm/?uid=131294&pwd=509c39d3eb5b363e8f4be986e0355178&cmd=send";
//		HttpRespons phr = request.sendPost(url);
//		System.out.println(phr.getContent());
	}
}
