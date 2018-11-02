package com.ixinhoo.shopping.controller.store;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.util.DataTableUtil;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dto.store.StoreClassifyDto;
import com.ixinhoo.shopping.entity.store.StoreClassify;
import com.ixinhoo.shopping.service.store.StoreClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品分类
 */
@RestController
@RequestMapping(value = "/api/bg/store-classify")
public class StoreClassifyController {

    @Autowired
    private StoreClassifyService service;


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(StoreClassify reqDto) {
        return service.saveStoreClassify(reqDto);
    }


    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public DetailDto<StoreClassify> findById(@PathVariable("id") Long id) {
        return new DetailDto<>(true, BeanMapper.map(service.findById(id), StoreClassify.class));
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public StatusDto delete(@RequestParam("ids") List<Long> ids) {
        service.deleteByIds(ids);
        return new StatusDto(true);
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<StoreClassify> list(HttpServletRequest request) {
        return service.listStoreClassify(DataTableUtil.toDataTable(request));
    }

    @RequestMapping(value = "list-all", method = RequestMethod.GET)
    public ListDto<StoreClassifyDto> findAll() {
        return service.findAllStoreClassify();
    }


}