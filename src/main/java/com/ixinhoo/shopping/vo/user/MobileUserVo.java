package com.ixinhoo.shopping.vo.user;

import com.ixinhoo.shopping.entity.user.MobileUser;

/**
 * 用户
 *
 * @author cici
 */
public class MobileUserVo extends MobileUser {
    private Integer ccStart;
    private Integer ccEnd;
    private String ccSort;
    private String ccField;
    private String phoneLike;

    public String getPhoneLike() {
        return phoneLike;
    }

    public void setPhoneLike(String phoneLike) {
        this.phoneLike = phoneLike;
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