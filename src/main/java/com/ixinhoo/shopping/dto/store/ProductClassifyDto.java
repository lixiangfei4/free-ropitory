package com.ixinhoo.shopping.dto.store;

import com.ixinhoo.shopping.entity.EntitySetting;

import java.util.List;

/**
 * 分类商品DTO
 *
 * @author cici
 */
public class ProductClassifyDto {
    private Long classifyId;//类别主键
    private String nameLike;//名称查询
    private Integer p;//页码
    private Integer s;//页大小
    private EntitySetting.ProductType type;//商城类型：普通/积分
    private String order;//排序（升序ASC、降序DESC）
    private String field;//积分排序传输 integral,价格排序传输price
    private List<String> category;//刷选类别名称  TODO cici
    private Integer beginIntegral;//开始积分
    private Integer endIntegral;//结束积分

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public EntitySetting.ProductType getType() {
        return type;
    }

    public void setType(EntitySetting.ProductType type) {
        this.type = type;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
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

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
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