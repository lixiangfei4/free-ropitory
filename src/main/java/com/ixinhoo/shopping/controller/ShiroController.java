package com.ixinhoo.shopping.controller;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.shiro.annotation.ProhibitedEntry;
import com.ixinhoo.shopping.dto.system.SystemUserDto;
import com.ixinhoo.shopping.service.system.SystemUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {

    @Autowired
    private SystemUserService service;


   
    @RequestMapping(value = "/api/bg/login", method = RequestMethod.GET)
    public StatusDto login() {
        Subject s = SecurityUtils.getSubject();
        return s.isAuthenticated() ? new StatusDto(true) :  new StatusDto(false);
    }

  
    @RequestMapping(value = "/api/bg/login", method = RequestMethod.POST)
    public DetailDto<SystemUserDto> login(SystemUserDto userDto) {
        return service.loginUser(userDto);
    }


}