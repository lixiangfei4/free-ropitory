package com.ixinhoo.shopping.entity.setting;

import com.ixinhoo.api.entity.AuditEntity;
import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 优惠券配置
 *
 * @author cici
 */
public class CouponSetting extends AuditEntity {
    private String name;
    private Double conditionPrice;
    private Double reducePrice;
    private EntitySetting.CouponType type;

    public EntitySetting.CouponType getType() {
        return type;
    }

    public void setType(EntitySetting.CouponType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getConditionPrice() {
        return conditionPrice;
    }

    public void setConditionPrice(Double conditionPrice) {
        this.conditionPrice = conditionPrice;
    }

    public Double getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(Double reducePrice) {
        this.reducePrice = reducePrice;
    }
}