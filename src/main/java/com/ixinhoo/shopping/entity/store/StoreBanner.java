package com.ixinhoo.shopping.entity.store;

import com.ixinhoo.api.entity.IdEntity;

/**
 * 门店banner
 *
 * @author cici
 */
public class StoreBanner extends IdEntity {
    private Long storeId;
    private String address;
    private Integer sort;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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