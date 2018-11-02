package com.ixinhoo.shopping.dto.order;

import com.ixinhoo.shopping.entity.EntitySetting;

import java.util.List;

/**
 * 用户订单列表dto
 *
 * @author 448778074@qq.com (cici)
 */
public class UserOrderListRspDto {
    private Long orderId;//订单id
    private String orderNum;//订单编号
    private EntitySetting.OrderStatus status;//订单状态
    private EntitySetting.YesOrNoType evaluated;//评价状态
    private EntitySetting.BuyProductType buyType;//取货类型（门店自提、快递）
    private String addressName;//地址名称
    private List<UserOrderProductDto> products;//商品列表

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public EntitySetting.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(EntitySetting.OrderStatus status) {
        this.status = status;
    }

    public EntitySetting.YesOrNoType getEvaluated() {
        return evaluated;
    }

    public void setEvaluated(EntitySetting.YesOrNoType evaluated) {
        this.evaluated = evaluated;
    }

    public EntitySetting.BuyProductType getBuyType() {
        return buyType;
    }

    public void setBuyType(EntitySetting.BuyProductType buyType) {
        this.buyType = buyType;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public List<UserOrderProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<UserOrderProductDto> products) {
        this.products = products;
    }
}
