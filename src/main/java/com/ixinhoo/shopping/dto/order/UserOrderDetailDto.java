package com.ixinhoo.shopping.dto.order;

import com.ixinhoo.shopping.dto.user.UserAndStoreAddressDto;
import com.ixinhoo.shopping.entity.EntitySetting;

import java.util.List;

/**
 * 用户订单详情dto
 *
 * @author 448778074@qq.com (cici)
 */
public class UserOrderDetailDto {
    private Long orderId;//订单id
    private String orderNum;//订单编号
    private EntitySetting.OrderStatus status;//订单状态
    private EntitySetting.YesOrNoType evaluated;//评价状态
    private EntitySetting.BuyProductType buyType;//取货类型（门店自提、快递）
    private Long orderTime;//提交时间
    private Long cancelTime;//取消时间
    private Long closeTime;//关闭时间
    private Long takeTime;//提货时间
    private Double price;//商品总价
    private Double integralPrice;//积分抵扣的价格
    private Integer integral;//使用的积分
    private Long couponId;//优惠券id
    private Double couponPrice;//优惠价格
    private UserAndStoreAddressDto address;//提货、收货地址
    private List<UserOrderProductDto> products;//商品列表

    public Long getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Long cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public Long getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Long takeTime) {
        this.takeTime = takeTime;
    }

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

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(Double integralPrice) {
        this.integralPrice = integralPrice;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Double getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Double couponPrice) {
        this.couponPrice = couponPrice;
    }

    public UserAndStoreAddressDto getAddress() {
        return address;
    }

    public void setAddress(UserAndStoreAddressDto address) {
        this.address = address;
    }

    public List<UserOrderProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<UserOrderProductDto> products) {
        this.products = products;
    }
}
