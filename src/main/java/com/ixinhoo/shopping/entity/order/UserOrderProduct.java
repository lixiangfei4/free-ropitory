package com.ixinhoo.shopping.entity.order;

import com.ixinhoo.api.entity.AuditEntity;
import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 订单商品
 *
 * @author cici
 */
public class UserOrderProduct extends AuditEntity {

    private Long userId;
    private Long orderId;
    private Long productId;
    private String orderNum;
    private String productName;//商品名称
    private String productRemark;//商品描述
    private Double productPrice;//商品原价价格
    private Integer productIntegral;//抵扣的积分
    private Double integralPrice;//积分抵扣的价格
    private String categoryName;//分类规格
    private Long couponId;//优惠券id
    private Double couponPrice;//优惠价格
    private String productImage;//商品logo
    private Integer productNum;//数目
    private Double realPrice;//真实支付价格
    private EntitySetting.ProductType productType;

    public EntitySetting.ProductType getProductType() {
        return productType;
    }

    public void setProductType(EntitySetting.ProductType productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getProductRemark() {
        return productRemark;
    }

    public void setProductRemark(String productRemark) {
        this.productRemark = productRemark;
    }

    public Integer getProductIntegral() {
        return productIntegral;
    }

    public void setProductIntegral(Integer productIntegral) {
        this.productIntegral = productIntegral;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Double getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(Double integralPrice) {
        this.integralPrice = integralPrice;
    }
}