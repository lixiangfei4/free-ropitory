package com.ixinhoo.shopping.entity.store;

import com.ixinhoo.api.entity.AuditEntity;
import com.ixinhoo.api.entity.IdEntity;
import com.ixinhoo.shopping.entity.EntitySetting;

import java.util.List;

/**
 * 商品
 *
 * @author cici
 */
public class Product extends AuditEntity {
    private String name;
    private String remark;
    private Integer integral;
    private Double price;
    private String detail;
    private String parameter;//产品参数
    private String jdLink;
    private Long collectCount;
    private Long buyCount;
    private Integer convertIntegral;
    private String image;
    private EntitySetting.ProductType type;
    private IdEntity.DataStatus status;
    private List<ProductBanner> banners;
    private Long classifyId;
    private Long couponId;

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getJdLink() {
        return jdLink;
    }

    public void setJdLink(String jdLink) {
        this.jdLink = jdLink;
    }

    public Long getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Long collectCount) {
        this.collectCount = collectCount;
    }

    public Long getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Long buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getConvertIntegral() {
        return convertIntegral;
    }

    public void setConvertIntegral(Integer convertIntegral) {
        this.convertIntegral = convertIntegral;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public EntitySetting.ProductType getType() {
        return type;
    }

    public void setType(EntitySetting.ProductType type) {
        this.type = type;
    }

    public DataStatus getStatus() {
        return status;
    }

    public void setStatus(DataStatus status) {
        this.status = status;
    }

    public List<ProductBanner> getBanners() {
        return banners;
    }

    public void setBanners(List<ProductBanner> banners) {
        this.banners = banners;
    }
}