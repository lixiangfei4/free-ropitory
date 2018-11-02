package com.ixinhoo.shopping.rest.user;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.entity.user.UserAddress;
import com.ixinhoo.shopping.service.user.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user-address")
public class UserAddressRestController {

    @Autowired
    private UserAddressService service;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(UserAddress reqDto) {
        return service.saveUserAddress(reqDto);
    }


    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public DetailDto<UserAddress> findById(@PathVariable("id") Long id) {
        return new DetailDto<>(true, BeanMapper.map(service.findById(id), UserAddress.class));
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ListDto<UserAddress> list(@RequestParam("userId") Long userId) {
        return service.listByUserId(userId);
    }

    @RequestMapping(value = "default", method = RequestMethod.POST)
    public StatusDto defaultAddress(@RequestParam("id") Long id) {
        return service.defaultAddressById(id);
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public StatusDto delete(@RequestParam("ids") List<Long> ids) {
        service.deleteByIds(ids);
        return new StatusDto(true);
    }


}