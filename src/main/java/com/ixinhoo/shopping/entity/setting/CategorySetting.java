package com.ixinhoo.shopping.entity.setting;

import com.ixinhoo.api.entity.AuditEntity;
import com.ixinhoo.api.entity.IdEntity;

/**
 * 商品类目配置
 *
 * @author cici
 */
public class CategorySetting extends AuditEntity {
    private String name;
    private Integer sort;
    private IdEntity.DataStatus status;

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

    public DataStatus getStatus() {
        return status;
    }

    public void setStatus(DataStatus status) {
        this.status = status;
    }
}