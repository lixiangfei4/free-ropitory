package com.ixinhoo.shopping.dto.store;

import com.ixinhoo.shopping.entity.setting.CouponSetting;
import com.ixinhoo.shopping.entity.user.UserAddress;

/**
 * 商品立即购买响应DTO
 *
 * @author cici
 */
public class ProductBuyRspDto {
    private StoreDto store;//门店
    private UserAddress address;//用户地址
    private ProductSimpleDto product;//商品信息
    private Integer integral;//用户积分数目
    private Double integralPrice;//一个积分可以兑换的商品价格
    private CouponSetting coupon;//可以使用的最优惠的优惠券

    public StoreDto getStore() {
        return store;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }

    public UserAddress getAddress() {
        return address;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }

    public ProductSimpleDto getProduct() {
        return product;
    }

    public void setProduct(ProductSimpleDto product) {
        this.product = product;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Double getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(Double integralPrice) {
        this.integralPrice = integralPrice;
    }

    public CouponSetting getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponSetting coupon) {
        this.coupon = coupon;
    }

}