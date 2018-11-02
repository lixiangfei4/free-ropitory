package com.ixinhoo.web.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ixinhoo.shopping.entity.setting.WeiXinConstants;

@Service
public class UserService {
	
	@Autowired
	private AccessTokenService accessTokenService;
    
    @Autowired
	private TokenGetByCCService tokenGetByCCService;

public static final ObjectMapper MAPPER = new ObjectMapper();
/**
 * 获取用户增减
 * @param begin_date
 * @param end_date
 * @return
 */
public String getUserAddOrdeduct(String begin_date,String end_date) {
    	DateEntity date = new DateEntity();
    	date.setBegin_date(begin_date);
    	date.setEnd_date(end_date);
    	try {
			//把date读成json
			String dateJson = MAPPER.writeValueAsString(date);
		//获取token
    	String tokenJSON = accessTokenService.getToken(WeiXinConstants.GET_TOKEN_URL);
    	Token tt = MAPPER.readValue(tokenJSON, Token.class);
			String tokenValue = tt.getAccess_token();
			//请求url
			String url ="https://api.weixin.qq.com/datacube/getusersummary?access_token="+tokenValue;
			String data =tokenGetByCCService.postJsonToken(url,dateJson);
			return data;
		} catch (Exception e) {
			
		}
		
    	return null;
    }
	
}
