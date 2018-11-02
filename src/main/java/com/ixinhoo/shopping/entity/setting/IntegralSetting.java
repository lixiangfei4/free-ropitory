package com.ixinhoo.shopping.entity.setting;

import com.ixinhoo.api.entity.AuditEntity;

/**
 * 积分配置
 *
 * @author cici
 */
public class IntegralSetting extends AuditEntity {
    private String name;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}