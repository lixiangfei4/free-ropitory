package com.ixinhoo.shopping.dto.order;

import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 用户订单购买dto
 *
 * @author 448778074@qq.com (cici)
 */
public class UserOrderBuyDto {
    private Long userId;//用户id
    private Long addressId;//地址id或者门店id。type是门店的时候为门店id、为快递的时候为地址id
    private Long productId;//商品id
    private Integer number;//商品数量
    private String category;//规则类型
    private Integer convertIntegral;//抵扣的积分数目
    private Long couponId;//优惠券id
    private EntitySetting.BuyProductType buy;//门店自提或快递

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getConvertIntegral() {
        return convertIntegral;
    }

    public void setConvertIntegral(Integer convertIntegral) {
        this.convertIntegral = convertIntegral;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }


    public EntitySetting.BuyProductType getBuy() {
        return buy;
    }

    public void setBuy(EntitySetting.BuyProductType buy) {
        this.buy = buy;
    }
}
