package com.ixinhoo.shopping.dto.setting;

/**
 * 系统用户dto
 *
 * @author 448778074@qq.com (cici)
 */
public class GradeSettingDto {
    private Long id;
    private String name;
    private Integer beginIntegral;
    private Integer endIntegral;
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
