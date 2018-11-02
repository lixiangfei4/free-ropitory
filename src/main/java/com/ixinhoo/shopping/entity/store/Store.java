package com.ixinhoo.shopping.entity.store;

import com.ixinhoo.api.entity.AuditEntity;

import java.util.List;

/**
 * 门店
 *
 * @author cici
 */
public class Store extends AuditEntity {
    private String name;
    private String city;
    private String province;
    private String area;
    private String image;
    private Double longitude;
    private Double latitude;
    private String address;
    private String phone;
    private String business;
    private List<StoreBanner> banners;
    private Double score;//评分
    private String contactName;//负责人、联系人
    private String contactPhone;//联系电话、负责人电话

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public List<StoreBanner> getBanners() {
        return banners;
    }

    public void setBanners(List<StoreBanner> banners) {
        this.banners = banners;
    }
}