package com.ixinhoo.web.weixin;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

@Service
public class TokenGetByCCService {
  /**
   * 从外网获取数据，相当于httpclient
   */
	@Autowired
	private RestTemplate restTemplate;
	
	//get请求
	public  String getTokenCC(String url) {
		String body = restTemplate.getForEntity(url, String.class).getBody();
		return body;
   }
	
	/**
	 * 获取文件请求
	 * @param url
	 * @return
	 */
	public  void get(String url) {
		restTemplate.getForEntity(url, null).getBody();
	
   }
	
	public String postMapToken(String url,Map<String,String> postData) {
		
        //json形式提交
      String s = restTemplate.postForEntity(url, postData, String.class).getBody();
		return s;
    }
	

	
	//传入参数为json类型的
    public String postJsonToken(String url,String data) {
		
        //json形式提交
      String s = restTemplate.postForEntity(url, data, String.class).getBody();
		return s;
    }
	
	
	public String postFormToken(String url) {
		//json形式提交
//      s = restTemplate.postForEntity(url, postData, String.class).getBody();
      //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递           
	MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
      //  也支持中文
      params.add("p", 0+"");
      params.add("s", 1+"");
      HttpHeaders headers = new HttpHeaders();
      //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
      String s = restTemplate.postForEntity(url, requestEntity, String.class).getBody();
      System.out.println(s);
	return s;
	}
}
