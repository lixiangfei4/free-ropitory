package com.ixinhoo.shopping.dao.store;

import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.shopping.entity.store.StoreBanner;
import com.ixinhoo.shopping.vo.store.StoreVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreDao<Store> extends BaseDao {

    int insertBanner(StoreBanner entity);

    int deleteBannerByStoreId(@Param("storeId") Long storeId);

    Store findStoreAndBannerById(@Param("id") Long id);

    List<Store> selectStoreByDistance(StoreVo vo);
}