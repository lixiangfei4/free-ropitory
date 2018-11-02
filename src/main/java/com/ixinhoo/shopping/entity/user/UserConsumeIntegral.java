
package com.ixinhoo.shopping.entity.user;

import com.ixinhoo.api.entity.AuditEntity;
import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 用户消费积分
 *
 * @author cici
 */
public class UserConsumeIntegral extends AuditEntity {
    private Long userId;
    private Long time;
    private Integer integral;
    private EntitySetting.ConsumeIntegralType type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public EntitySetting.ConsumeIntegralType getType() {
        return type;
    }

    public void setType(EntitySetting.ConsumeIntegralType type) {
        this.type = type;
    }
}