package com.ixinhoo.shopping.entity.store;

import com.ixinhoo.api.entity.AuditEntity;

/**
 * 商品规则
 *
 * @author cici
 */
public class ProductCategory extends AuditEntity {
    private Long productId;
    private Long categoryId;
    private String name;
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}