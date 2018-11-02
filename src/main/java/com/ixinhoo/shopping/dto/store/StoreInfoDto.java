package com.ixinhoo.shopping.dto.store;

/**
 * 门店列表DTO
 *
 * @author cici
 */
public class StoreInfoDto extends StoreDto {
    private Double distance;//距离

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}