package com.ixinhoo.shopping.dto.store;

/**
 * 商品分类
 *
 * @author cici
 */
public class StoreClassifyDto {
    private Long id;
    private String name;
    private Integer sort;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}