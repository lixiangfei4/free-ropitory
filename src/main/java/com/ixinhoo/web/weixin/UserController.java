package com.ixinhoo.web.weixin;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="api/bg/user-analysis")
public class UserController {
    @Autowired
	TokenGetByCCService tokenGetByCCService;
   
    
    public String token="412414470";
	
	@RequestMapping(value="get",method=RequestMethod.GET)
	public File getUserInfo(String begin_date,String end_date){
		
  
    return null;
  
  }
	}
