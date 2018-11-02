package com.ixinhoo.shopping.controller.system;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.util.DataTableUtil;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dto.system.StoreSystemUserDto;
import com.ixinhoo.shopping.dto.system.SystemUserDto;
import com.ixinhoo.shopping.entity.system.SystemUser;
import com.ixinhoo.shopping.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bg/system-user")
public class SystemUserController {

    @Autowired
    private SystemUserService service;


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(SystemUserDto userDto) {
        return service.saveSystemUser(userDto);
    }


    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public DetailDto<SystemUserDto> findById(@PathVariable("id") Long id) {
        return new DetailDto<>(true, BeanMapper.map(service.findById(id),SystemUserDto.class));
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public StatusDto delete(@RequestParam("ids") List<Long> ids) {
        service.deleteByIds(ids);
        return new StatusDto(true);
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<SystemUserDto> list(HttpServletRequest request) {
        return service.listSystemUser(DataTableUtil.toDataTable(request));
    }

    @RequestMapping(value = "store/{id}", method = RequestMethod.GET)
    public ListDto<StoreSystemUserDto> findStoreManagerById(@PathVariable("id") Long id) {
        return service.findStoreManagerByStoreId(id);
    }

    @RequestMapping(value = "store-save", method = RequestMethod.POST)
    public StatusDto saveStoreManager(@RequestParam("userId") Long userId,@RequestParam("storeId")Long storeId) {
        return service.saveStoreManager(userId,storeId);
    }



}