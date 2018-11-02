package com.ixinhoo.shopping.controller.setting;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.util.DataTableUtil;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.entity.setting.HomeBannerSetting;
import com.ixinhoo.shopping.service.setting.HomeBannerSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页banner
 */
@RestController
@RequestMapping(value = "/api/bg/banner-setting")
public class HomeBannerSettingController {

    @Autowired
    private HomeBannerSettingService service;


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(HomeBannerSetting reqDto) {
        return service.saveHomeBannerSetting(reqDto);
    }


    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public DetailDto<HomeBannerSetting> findById(@PathVariable("id") Long id) {
        return new DetailDto<>(true, BeanMapper.map(service.findById(id), HomeBannerSetting.class));
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public StatusDto delete(@RequestParam("ids") List<Long> ids) {
        service.deleteByIds(ids);
        return new StatusDto(true);
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<HomeBannerSetting> list(HttpServletRequest request) {
        return service.listHomeBannerSetting(DataTableUtil.toDataTable(request));
    }
}