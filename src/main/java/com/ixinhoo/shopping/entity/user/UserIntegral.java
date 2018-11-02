package com.ixinhoo.shopping.entity.user;

import com.ixinhoo.api.entity.AuditEntity;

/**
 * 用户签到 TODO  cici
 *
 * @author cici
 */
public class UserIntegral extends AuditEntity {
    private Long userId;
    private Long signTime;
    private Integer integral;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSignTime() {
        return signTime;
    }

    public void setSignTime(Long signTime) {
        this.signTime = signTime;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}