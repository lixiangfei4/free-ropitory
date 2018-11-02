package com.ixinhoo.shopping.entity.user;

import com.ixinhoo.api.entity.AuditEntity;
import com.ixinhoo.api.entity.IdEntity;
import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 短信
 *
 * @author cici
 */
public class MessageCode extends AuditEntity {
    private String phone;
    private String code;
    private String message;
    private Long sendTime;
    private IdEntity.DataStatus status;
    private Long invalidTime;
    private EntitySetting.MessageCodeType type;//类型

    public EntitySetting.MessageCodeType getType() {
        return type;
    }

    public void setType(EntitySetting.MessageCodeType type) {
        this.type = type;
    }

    public Long getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Long invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public DataStatus getStatus() {
        return status;
    }

    public void setStatus(DataStatus status) {
        this.status = status;
    }
}