package com.ixinhoo.web.weixin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class AccessTokenService {
	@Autowired
	private TokenGetByCCService tokenGetByCCService;
	/**
	 * 获取token
	 * @return
	 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
	 */
	
	public static final Long EXPIRES_IN= 7200L;
	//上一个用户来获取token的时间
	public static Long lastTime ;
	public static final ObjectMapper MAPPER = new ObjectMapper();
	Map<String,Object> map = new HashMap<String,Object>();
	Token token;
	
  public String getToken(String url) {
    try {
    	if(null==map.get("access_token")) {
    	   String tokenJSON =tokenGetByCCService.getTokenCC(url);
    	   token = MAPPER.readValue(tokenJSON, Token.class);
    		map.put("access_token", token.getAccess_token());
    		map.put("expires_in", token.getExpires_in());
    		//记录上次获取的时间
    		lastTime = new Date().getTime();
    		
    		return tokenJSON;
    	};
    	//在时间段内
    	if((System.currentTimeMillis()-lastTime)<=EXPIRES_IN*1000) {
    		
    		return MAPPER.writeValueAsString(token);
        	}	
    	//超出时间段，创建token
    	if((System.currentTimeMillis()-lastTime)>EXPIRES_IN*1000) {
		//把token串转化为TOKEN对象
		token = MAPPER.readValue(tokenGetByCCService.getTokenCC(url), Token.class);
		//把token存到map中
		map.put("access_token", token.getAccess_token());
		map.put("expires_in", token.getExpires_in());
		return tokenGetByCCService.getTokenCC(url);
    	}	
		
	} catch (Exception e) {
		e.printStackTrace();
	} 
	return null;
	}
}
