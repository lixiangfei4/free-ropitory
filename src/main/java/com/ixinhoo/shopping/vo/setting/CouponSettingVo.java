package com.ixinhoo.shopping.vo.setting;

import com.ixinhoo.shopping.entity.setting.CouponSetting;


public class CouponSettingVo extends CouponSetting {
    private Integer ccStart;
    private Integer ccEnd;
    private String ccSort;
    private String ccField;

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
