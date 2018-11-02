package com.ixinhoo.shopping.rest.store;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.shopping.dto.order.UserOrderListReqDto;
import com.ixinhoo.shopping.dto.order.UserOrderListRspDto;
import com.ixinhoo.shopping.dto.store.StoreDetailReqDto;
import com.ixinhoo.shopping.dto.store.StoreInfoDto;
import com.ixinhoo.shopping.dto.store.StoreManagerInfoDto;
import com.ixinhoo.shopping.dto.store.StorePageDto;
import com.ixinhoo.shopping.dto.system.UserStoreEvaluateDto;
import com.ixinhoo.shopping.service.store.StoreManagerService;
import com.ixinhoo.shopping.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/store-manager")
public class StoreManagerRestController {

    @Autowired
    private StoreManagerService service;


    /**
     * 门店详情
     *
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.POST)
    public DetailDto<StoreManagerInfoDto> info(@RequestParam("storeId") Long id) {
        return service.findStoreManagerInfoById(id);
    }



    /**
     * 门店订单列表
     *
     * @return
     */
    @RequestMapping(value = "order-list", method = RequestMethod.POST)
    public ListDto<UserOrderListRspDto> orderList(UserOrderListReqDto reqDto) {
        return service.pageOrderList(reqDto);
    }


    /**
     * 门店订单--评级啊列表
     *
     * @return
     */
    @RequestMapping(value = "order-evaluate", method = RequestMethod.POST)
    public ListDto<UserStoreEvaluateDto> orderEvaluate(@RequestParam("storeId") Long storeId, Long orderId,Integer p, Integer s) {
        return service.pageOrderEvaluate(storeId,orderId,p,s);
    }


}