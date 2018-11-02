package com.ixinhoo.shopping.entity.store;

import com.ixinhoo.api.entity.AuditEntity;

/**
 * 商品分类
 *
 * @author cici
 */
public class StoreClassify extends AuditEntity {
    private String name;
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}