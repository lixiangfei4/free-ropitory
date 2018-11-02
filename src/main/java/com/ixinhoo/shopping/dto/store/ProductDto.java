package com.ixinhoo.shopping.dto.store;

import com.ixinhoo.api.entity.IdEntity;
import com.ixinhoo.shopping.entity.EntitySetting;

import java.util.List;

/**
 * 商品DTO
 *
 * @author cici
 */
public class ProductDto {
    private Long id;
    private String name;
    private String remark;
    private Integer integral;
    private Double price;
    private String detail;
    private String parameter;
    private String jdLink;
    private Long collectCount;
    private Long buyCount;
    private Integer convertIntegral;
    private String image;
    private EntitySetting.ProductType type;
    private IdEntity.DataStatus status;
    private List<String> bannerList;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public IdEntity.DataStatus getStatus() {
        return status;
    }

    public void setStatus(IdEntity.DataStatus status) {
        this.status = status;
    }

    public List<String> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<String> bannerList) {
        this.bannerList = bannerList;
    }
}