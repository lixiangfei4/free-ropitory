package com.ixinhoo.shopping.dto.store;

/**
 * 商品优惠券DTO
 *
 * @author cici
 */
public class ProductCouponDto {
    private Long productId;
    private Long couponId;
    private String couponName;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}