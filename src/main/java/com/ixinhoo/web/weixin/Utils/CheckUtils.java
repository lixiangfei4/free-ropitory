package com.ixinhoo.web.weixin.Utils;

import java.security.MessageDigest;
import java.util.Arrays;

public class CheckUtils {

	public static final String token="ccc";
	public static boolean check(String signature,String timestamp,String nonce) {
		String[] a = new String[] {token,timestamp,nonce};
		//排序
		Arrays.sort(a);
		//连接成字符串
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<a.length;i++) {
			sb.append(a[i]);
		}
		
		String str = sb.toString();
		//获得加密后的str
		String s = getSha1(str);
		return s.equals(signature);
	}
	/**
	 * 加密
	 * @param str
	 * @return
	 */
	//sha1加密
	  public static String getSha1(String str){
	        if(str==null||str.length()==0){
	            return null;
	        }
	        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
	                'a','b','c','d','e','f'};
	        try {
	            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
	            mdTemp.update(str.getBytes("UTF-8"));

	            byte[] md = mdTemp.digest();
	            int j = md.length;
	            char buf[] = new char[j*2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                buf[k++] = hexDigits[byte0 & 0xf];      
	            }
	            return new String(buf);
	        } catch (Exception e) {
	            // TODO: handle exception
	            return null;
	        }
	    }
}
