package com.ixinhoo.shopping.dto.store;

import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 商品简单的DTO-下单、列表时可使用
 *
 * @author cici
 */
public class ProductSimpleDto {
    private Long id;
    private String name;
    private String remark;
    private Integer number;//购买数量
    private String categoryName;//分类名称
    private Double price;//当前用户购买时候的价格
    private Integer convertIntegral;//抵扣的积分
    private EntitySetting.ProductType type;
    private String image;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getConvertIntegral() {
        return convertIntegral;
    }

    public void setConvertIntegral(Integer convertIntegral) {
        this.convertIntegral = convertIntegral;
    }
}