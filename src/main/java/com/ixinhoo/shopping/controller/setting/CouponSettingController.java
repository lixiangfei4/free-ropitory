package com.ixinhoo.shopping.controller.setting;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.util.DataTableUtil;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.entity.EntitySetting;
import com.ixinhoo.shopping.entity.setting.CouponSetting;
import com.ixinhoo.shopping.service.setting.CouponSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 优惠券
 */
@RestController
@RequestMapping(value = "/api/bg/coupon-setting")
public class CouponSettingController {

    @Autowired
    private CouponSettingService service;


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(CouponSetting reqDto) {
        return service.saveCouponSetting(reqDto);
    }


    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public DetailDto<CouponSetting> findById(@PathVariable("id") Long id) {
        return new DetailDto<>(true, BeanMapper.map(service.findById(id), CouponSetting.class));
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public StatusDto delete(@RequestParam("ids") List<Long> ids) {
        service.deleteByIds(ids);
        return new StatusDto(true);
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<CouponSetting> list(HttpServletRequest request) {
        return service.listCouponSetting(DataTableUtil.toDataTable(request));
    }

    @RequestMapping(value = "list-type", method = RequestMethod.POST)
    public ListDto<CouponSetting> listCouponByType(@RequestParam("type") EntitySetting.CouponType type) {
        return service.listCouponByType(type);
    }

}