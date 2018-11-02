package com.ixinhoo.shopping.entity.user;

import com.ixinhoo.api.entity.AuditEntity;

/**
 * 用户的手机号
 *
 * @author cici
 */
public class UserPhone extends AuditEntity {
    private Long userId;//用户手机号
    private String phone;
    private Long time;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}