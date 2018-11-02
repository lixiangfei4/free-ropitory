package com.ixinhoo.web.weixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ixinhoo.shopping.entity.setting.WeiXinConstants;

@RestController
@RequestMapping(value="weixin")
public class TokenController {
    @Autowired
	private AccessTokenService accessTokenService;
    
    @Autowired
	private TokenGetByCCService tokenGetByCCService;
    
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER =LoggerFactory.getLogger(TokenController.class);
    
    /**
     * 获取用户列表和总条数
     * @return
     */
    @RequestMapping(value="getUser",method=RequestMethod.GET)
    public String getUserListInfo() {
    	//获取token
    	String tokenJSON = accessTokenService.getToken(WeiXinConstants.GET_TOKEN_URL);
    	
    	try {
    		//转化为token对象
			Token t = MAPPER.readValue(tokenJSON, Token.class);
			//得到token的值
	    	String tokenValue = t.getAccess_token();
	    	//把token的值写入到url中
	    	String userListUrl="https://api.weixin.qq.com/cgi-bin/user/get?access_token="+tokenValue+"&next_openid=";
	    	//根据连接查询接口
	    	String userInfo = tokenGetByCCService.getTokenCC(userListUrl);
	    	return userInfo;
		} catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
			LOGGER.info("转化对象异常",e.getMessage());
			}
		}
		return null;
    	
    }
    /**
     * 获取用户的增加信息，只限7天只内，如1-11到1-17
     * @param begin_date
     * @param end_date
     * @return
     */
    @RequestMapping(value="userCountChange",method=RequestMethod.POST)
    public String getUserAddOrdeduct(String begin_date,String end_date) {
    	
    	if(LOGGER.isInfoEnabled()) {
			LOGGER.info("参数有误"+begin_date+"end_date"+end_date,"参数有误");
		}
    	
    	DateEntity date = new DateEntity();
    	date.setBegin_date(begin_date);
    	date.setEnd_date(end_date);
    	//把date读成json
    	
		try {
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
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("转化对象异常",e.getMessage());
			}
		}
		if(LOGGER.isErrorEnabled()) {
			LOGGER.error("系统异常，请重试",begin_date);
		}
    	return null;
    }
}
