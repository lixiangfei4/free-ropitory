package com.ixinhoo.shopping.dto.user;

import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 用户评价DTO
 */
public class UserEvaluateDto {
    private Long dataId;//数据id（门店id）
    private Double score;//分数
    private Long userId;//用户id
    private String phone;//手机号
    private String content;//评价内容
    private EntitySetting.UserEvaluateType type = EntitySetting.UserEvaluateType.STORE;

    public EntitySetting.UserEvaluateType getType() {
        return type;
    }

    public void setType(EntitySetting.UserEvaluateType type) {
        this.type = type;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}