package com.ixinhoo.shopping.entity.user;

import com.ixinhoo.api.entity.AuditEntity;
import com.ixinhoo.shopping.entity.EntitySetting;

/**
 * 用户地址
 *
 * @author cici
 */
public class UserAddress extends AuditEntity {
    private Long userId;
    private String province;
    private String city;
    private String area;
    private String address;
    private String postCode;
    private String name;
    private String phone;
    private EntitySetting.YesOrNoType isDefault;
    private Double longitude;//经度
    private Double latitude;//纬度

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EntitySetting.YesOrNoType getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(EntitySetting.YesOrNoType isDefault) {
        this.isDefault = isDefault;
    }
}