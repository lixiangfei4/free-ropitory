package com.ixinhoo.shopping.entity.store;

import com.ixinhoo.api.entity.IdEntity;

/**
 * 商品banner
 *
 * @author cici
 */
public class ProductBanner extends IdEntity {
    private Long productId;
    private String address;
    private Integer sort;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}