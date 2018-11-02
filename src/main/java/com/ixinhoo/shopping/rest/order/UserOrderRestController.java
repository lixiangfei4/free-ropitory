package com.ixinhoo.shopping.rest.order;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.shopping.dto.order.UserOrderBuyDto;
import com.ixinhoo.shopping.dto.order.UserOrderDetailDto;
import com.ixinhoo.shopping.dto.order.UserOrderListReqDto;
import com.ixinhoo.shopping.dto.order.UserOrderListRspDto;
import com.ixinhoo.shopping.service.order.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user-order")
public class UserOrderRestController {

    @Autowired
    private UserOrderService service;


    /**
     * 立即购买确认
     *
     * @return
     */
    @RequestMapping(value = "buy", method = RequestMethod.POST)
    public StatusDto buy(UserOrderBuyDto reqDto) {
        return service.buyProduct(reqDto);
    }


    /**
     * 我的订单分页
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ListDto<UserOrderListRspDto> list(UserOrderListReqDto reqDto) {
        return service.pageMyOrder(reqDto);
    }

    /**
     * 我的订单详情;
     *
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public DetailDto<UserOrderDetailDto> detail(@RequestParam("orderId") Long orderId) {
        return service.findUserOrderDetailByOrderId(orderId);
    }


    /**
     * 取消订单;
     *
     * @return
     */
    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    public StatusDto cancel(@RequestParam("orderId") Long orderId) {
        return service.cancelUserOrderByOrderId(orderId);
    }

    /**
     * 删除订单;
     *
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public StatusDto delete(@RequestParam("orderId") Long orderId) {
        return service.deleteUserOrderByOrderId(orderId);
    }





}