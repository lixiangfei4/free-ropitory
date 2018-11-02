package com.ixinhoo.shopping.dto.store;

/**
 * 门店详情DTO
 *
 * @author cici
 */
public class StoreDetailReqDto  {
    private Long id;
    private Double la;
    private Double lg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLa() {
        return la;
    }

    public void setLa(Double la) {
        this.la = la;
    }

    public Double getLg() {
        return lg;
    }

    public void setLg(Double lg) {
        this.lg = lg;
    }
}