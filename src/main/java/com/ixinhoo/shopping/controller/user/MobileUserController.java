package com.ixinhoo.shopping.controller.user;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.util.DataTableUtil;
import com.ixinhoo.shopping.dto.user.MobileUserDto;
import com.ixinhoo.shopping.entity.user.MobileUser;
import com.ixinhoo.shopping.service.user.MobileUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bg/user")
public class MobileUserController {

    @Autowired
    private MobileUserService service;


    @RequestMapping(value = "show-integral/{id}", method = RequestMethod.GET)
    public DetailDto<Long> findById(@PathVariable("id") Long id) {
        MobileUser user = (MobileUser) service.findById(id);
        return user == null ? new DetailDto<>(false) : new DetailDto<>(true, user.getIntegral());
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<MobileUserDto> list(HttpServletRequest request) {
        return service.listMobileUser(DataTableUtil.toDataTable(request));
    }


    @RequestMapping(value = "save-integral", method = RequestMethod.POST)
    public StatusDto saveIntegral(@RequestParam("id")Long id,@RequestParam("integral")Long integral) {
        return service.updateUserIntegralById(id,integral);
    }
}