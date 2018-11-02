package com.ixinhoo.shopping.controller.setting;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.util.DataTableUtil;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dto.setting.GradeSettingDto;
import com.ixinhoo.shopping.entity.setting.IntegralSetting;
import com.ixinhoo.shopping.service.setting.IntegralSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 积分
 */
@RestController
@RequestMapping(value = "/api/bg/integral-setting")
public class IntegralSettingController {

    @Autowired
    private IntegralSettingService service;


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(IntegralSetting reqDto) {
        return service.saveIntegralSetting(reqDto);
    }


    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public DetailDto<IntegralSetting> findById(@PathVariable("id") Long id) {
        return new DetailDto<>(true, BeanMapper.map(service.findById(id), IntegralSetting.class));
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public StatusDto delete(@RequestParam("ids") List<Long> ids) {
        service.deleteByIds(ids);
        return new StatusDto(true);
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<IntegralSetting> list(HttpServletRequest request) {
        return service.listIntegralSetting(DataTableUtil.toDataTable(request));
    }
}