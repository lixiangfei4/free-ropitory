package com.ixinhoo.shopping.dto.order;

import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 用户订单请求列表dto
 *
 * @author 448778074@qq.com (cici)
 */
public class UserOrderListReqDto {
    private Long userId;//用户订单列表使用
    private Long storeId;//门店订单列表使用
    private EntitySetting.OrderStatus status;//订单状态
    private Integer p;
    private Integer s;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EntitySetting.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(EntitySetting.OrderStatus status) {
        this.status = status;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }
}
