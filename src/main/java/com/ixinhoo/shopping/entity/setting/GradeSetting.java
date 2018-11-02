package com.ixinhoo.shopping.entity.setting;

import com.ixinhoo.api.entity.AuditEntity;

/**
 * 等级配置
 *
 * @author cici
 */
public class GradeSetting extends AuditEntity {
    private String name;
    private Integer beginIntegral;
    private Integer endIntegral;
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBeginIntegral() {
        return beginIntegral;
    }

    public void setBeginIntegral(Integer beginIntegral) {
        this.beginIntegral = beginIntegral;
    }

    public Integer getEndIntegral() {
        return endIntegral;
    }

    public void setEndIntegral(Integer endIntegral) {
        this.endIntegral = endIntegral;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}