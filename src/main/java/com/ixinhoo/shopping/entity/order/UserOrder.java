package com.ixinhoo.shopping.entity.order;

import com.ixinhoo.api.entity.AuditEntity;
import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 订单
 *
 * @author cici
 */
public class UserOrder extends AuditEntity {
    private Long userId;
    private EntitySetting.BuyProductType buyType;
    private Long addressId;
    private String orderNum;
    private Double orderPrice;
    private Integer orderIntegral;
    private EntitySetting.OrderStatus orderStatus;
    private EntitySetting.YesOrNoType evaluated;
    private Integer productNum;
    private Long orderTime;
    private Long cancelTime;
    private Long closeTime;
    private Long takeTime;

    public Integer getOrderIntegral() {
        return orderIntegral;
    }

    public void setOrderIntegral(Integer orderIntegral) {
        this.orderIntegral = orderIntegral;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EntitySetting.BuyProductType getBuyType() {
        return buyType;
    }

    public void setBuyType(EntitySetting.BuyProductType buyType) {
        this.buyType = buyType;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public EntitySetting.OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(EntitySetting.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public EntitySetting.YesOrNoType getEvaluated() {
        return evaluated;
    }

    public void setEvaluated(EntitySetting.YesOrNoType evaluated) {
        this.evaluated = evaluated;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

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
}