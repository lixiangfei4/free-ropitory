package com.ixinhoo.shopping.rest.store;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.shopping.dto.store.StoreDetailReqDto;
import com.ixinhoo.shopping.dto.store.StoreInfoDto;
import com.ixinhoo.shopping.dto.store.StorePageDto;
import com.ixinhoo.shopping.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/store")
public class StoreRestController {

    @Autowired
    private StoreService service;


    /**
     * 门店列表，分页
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ListDto<StoreInfoDto> list(StorePageDto reqDto) {
        return service.pageStore(reqDto);
    }


    /**
     * 门店详情
     *
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public DetailDto<StoreInfoDto> detail(StoreDetailReqDto reqDto) {
        return service.findStoreDetail(reqDto);
    }


}