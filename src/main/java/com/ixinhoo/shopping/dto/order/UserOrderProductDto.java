package com.ixinhoo.shopping.dto.order;

import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 用户订单商品dto
 *
 * @author 448778074@qq.com (cici)
 */
public class UserOrderProductDto {
    private Long id;//商品id
    private Integer number;//商品数量
    private String category;//规则类型
    private Integer convertIntegral;//抵扣的积分数目
    private Double integralPrice;//积分抵扣的价格
    private Double price;//商品价格，商品实际付款价格
    private EntitySetting.ProductType type;//商品类型、积分商城/普通商城
    private String name;//商品名称
    private String remark;//商品备注
    private String image;//商品logo

    public Double getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(Double integralPrice) {
        this.integralPrice = integralPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getConvertIntegral() {
        return convertIntegral;
    }

    public void setConvertIntegral(Integer convertIntegral) {
        this.convertIntegral = convertIntegral;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public EntitySetting.ProductType getType() {
        return type;
    }

    public void setType(EntitySetting.ProductType type) {
        this.type = type;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
