package com.ixinhoo.shopping.entity.setting;

import com.ixinhoo.api.entity.AuditEntity;
import com.ixinhoo.api.entity.IdEntity;

/**
 * 首页banner
 *
 * @author cici
 */
public class HomeBannerSetting extends AuditEntity {
    private String name;
    private String link;
    private IdEntity.DataStatus status;
    private String remark;
    private String image;
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public DataStatus getStatus() {
        return status;
    }

    public void setStatus(DataStatus status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}