package com.ixinhoo.shopping.dao.store;

import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.entity.IdEntity;
import com.ixinhoo.shopping.entity.store.Product;
import com.ixinhoo.shopping.entity.store.ProductBanner;
import com.ixinhoo.shopping.vo.store.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao extends BaseDao {

    int insertBanner(ProductBanner entity);

    int deleteBannerByProductId(@Param("productId") Long productId);

    Product findProductAndBannerById(@Param("id") Long id);

    int updateStatusByIds(@Param("ids") List<Long> ids, @Param("status") IdEntity.DataStatus status);

    List<Product> selectByClassifyId(ProductVo entity);

    List<Product> selectProduct(ProductVo entity);

    List<Product> selectByIds(@Param("ids") List<Long> ids);
}