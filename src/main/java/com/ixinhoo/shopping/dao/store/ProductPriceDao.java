package com.ixinhoo.shopping.dao.store;

import com.ixinhoo.api.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductPriceDao<ProductPrice> extends BaseDao {

    List<ProductPrice> selectByProductId(@Param("productId") Long productId);
}