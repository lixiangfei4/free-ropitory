package com.ixinhoo.shopping.dao.store;

import com.ixinhoo.api.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao<ProductCategory> extends BaseDao {

    int deleteByProductId(@Param("productId") Long productId);
    List<ProductCategory> selectByProductId(@Param("productId") Long productId);
}