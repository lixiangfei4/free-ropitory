package com.ixinhoo.shopping.rest.system;


import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.shopping.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/system-user")
public class SystemUserRestController {

    @Autowired
    private SystemUserService service;


    @RequestMapping(value = "store-login", method = RequestMethod.POST)
    public StatusDto login(@RequestParam("loginName") String loginName, @RequestParam("password") String password) {
        return service.loginStoreUser(loginName, password);
    }


}