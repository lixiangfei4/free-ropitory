package com.ixinhoo.shopping.entity.store;

/**
 * 商品优惠券
 *
 * @author cici
 */
public class ProductCoupon {
    private Long productId;
    private Long couponId;

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
}