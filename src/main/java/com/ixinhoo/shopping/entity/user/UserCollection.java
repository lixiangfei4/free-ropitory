package com.ixinhoo.shopping.entity.user;

import com.ixinhoo.api.entity.AuditEntity;
import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 用户的收藏
 *
 * @author cici
 */
public class UserCollection extends AuditEntity {
    private Long userId;//用户id
    private Long dataId;//数据id
    private Long time;//收藏时间
    private EntitySetting.UserCollectionType type = EntitySetting.UserCollectionType.PRODUCT;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public EntitySetting.UserCollectionType getType() {
        return type;
    }

    public void setType(EntitySetting.UserCollectionType type) {
        this.type = type;
    }
}