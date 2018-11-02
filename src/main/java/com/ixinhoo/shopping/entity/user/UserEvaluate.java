package com.ixinhoo.shopping.entity.user;

import com.ixinhoo.api.entity.AuditEntity;
import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 用户商品评价
 *
 * @author cici
 */
public class UserEvaluate extends AuditEntity {
    private Long userId;
    private String phone;
    private Double score;
    private Long dataId;
    private Long time;
    private String content;
    private EntitySetting.UserEvaluateType type = EntitySetting.UserEvaluateType.STORE;

    public EntitySetting.UserEvaluateType getType() {
        return type;
    }

    public void setType(EntitySetting.UserEvaluateType type) {
        this.type = type;
    }

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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}