package com.ixinhoo.shopping.vo.store;

import com.ixinhoo.shopping.entity.store.Product;

import java.util.List;


public class ProductVo extends Product {
    private Integer ccStart;
    private Integer ccEnd;
    private String ccSort;
    private String ccField;
    private String nameLike;
    private List<String> category;
    private Integer beginIntegral;
    private Integer endIntegral;

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

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public Integer getCcStart() {
        return ccStart;
    }

    public void setCcStart(Integer ccStart) {
        this.ccStart = ccStart;
    }

    public Integer getCcEnd() {
        return ccEnd;
    }

    public void setCcEnd(Integer ccEnd) {
        this.ccEnd = ccEnd;
    }

    public String getCcSort() {
        return ccSort;
    }

    public void setCcSort(String ccSort) {
        this.ccSort = ccSort;
    }

    public String getCcField() {
        return ccField;
    }

    public void setCcField(String ccField) {
        this.ccField = ccField;
    }
}
