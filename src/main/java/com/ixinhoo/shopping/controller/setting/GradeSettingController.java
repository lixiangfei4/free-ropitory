package com.ixinhoo.shopping.controller.setting;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.util.DataTableUtil;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dto.setting.GradeSettingDto;
import com.ixinhoo.shopping.service.setting.GradeSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 等级
 */
@RestController
@RequestMapping(value = "/api/bg/grade-setting")
public class GradeSettingController {

    @Autowired
    private GradeSettingService service;


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(GradeSettingDto reqDto) {
        return service.saveGradeSetting(reqDto);
    }


    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public DetailDto<GradeSettingDto> findById(@PathVariable("id") Long id) {
        return new DetailDto<>(true, BeanMapper.map(service.findById(id), GradeSettingDto.class));
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public StatusDto delete(@RequestParam("ids") List<Long> ids) {
        service.deleteByIds(ids);
        return new StatusDto(true);
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<GradeSettingDto> list(HttpServletRequest request) {
        return service.listGradeSetting(DataTableUtil.toDataTable(request));
    }
}