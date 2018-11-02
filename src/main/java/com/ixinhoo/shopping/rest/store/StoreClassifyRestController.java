package com.ixinhoo.shopping.rest.store;


import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.shopping.dto.store.StoreClassifyDto;
import com.ixinhoo.shopping.service.store.StoreClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/store-classify")
public class StoreClassifyRestController {

    @Autowired
    private StoreClassifyService service;


    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ListDto<StoreClassifyDto> list(Integer p, Integer s) {
        return service.findStoreClassify(p, s);
    }


}