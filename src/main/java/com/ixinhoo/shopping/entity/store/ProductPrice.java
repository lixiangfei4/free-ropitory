package com.ixinhoo.shopping.entity.store;

import com.ixinhoo.api.entity.AuditEntity;

/**
 * 商品价格
 *
 * @author cici
 */
public class ProductPrice extends AuditEntity {
    private Long productId;
    private Long gradeId;
    private Integer integral;
    private Double price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
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
}