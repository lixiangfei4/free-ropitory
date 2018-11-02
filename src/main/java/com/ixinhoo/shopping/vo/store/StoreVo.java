package com.ixinhoo.shopping.vo.store;

import com.ixinhoo.shopping.entity.store.Store;


public class StoreVo extends Store {
    private Integer ccStart;
    private Integer ccEnd;
    private String ccSort;
    private String ccField;
    private String nameLike;

    public String getNameLike() {
        return nameLike;
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

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }
}
