package com.ixinhoo.shopping.controller.store;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.util.DataTableUtil;
import com.ixinhoo.shopping.dto.store.StoreDto;
import com.ixinhoo.shopping.entity.store.Store;
import com.ixinhoo.shopping.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bg/store")
public class StoreController {

    @Autowired
    private StoreService service;


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(StoreDto reqDto) {
        return service.saveStore(reqDto);
    }


    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public DetailDto<StoreDto> findById(@PathVariable("id") Long id) {
        return service.findStoreAndBannerById(id);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public StatusDto delete(@RequestParam("ids") List<Long> ids) {
        service.deleteByIds(ids);
        return new StatusDto(true);
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<Store> list(HttpServletRequest request) {
        return service.listStore(DataTableUtil.toDataTable(request));
    }


}