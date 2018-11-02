package com.ixinhoo.shopping.dto.store;

/**
 * 门店管理信息DTO
 *
 * @author cici
 */
public class StoreManagerInfoDto {
    private String name;//门店名称
    private Double score;//分数
    private Integer taking;//未完成
    private Integer taken;//已完成
    private Integer cancel;//取消

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getTaking() {
        return taking;
    }

    public void setTaking(Integer taking) {
        this.taking = taking;
    }

    public Integer getTaken() {
        return taken;
    }

    public void setTaken(Integer taken) {
        this.taken = taken;
    }

    public Integer getCancel() {
        return cancel;
    }

    public void setCancel(Integer cancel) {
        this.cancel = cancel;
    }
}