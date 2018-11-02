package com.ixinhoo.shopping.dto.system;

/**
 * 门店系统用户dto
 *
 * @author 448778074@qq.com (cici)
 */
public class StoreSystemUserDto {
    private Long id;
    private String name;//用户名
    private Long storeId;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

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
}
