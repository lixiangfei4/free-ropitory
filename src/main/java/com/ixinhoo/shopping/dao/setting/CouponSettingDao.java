package com.ixinhoo.shopping.dao.setting;

import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.shopping.entity.EntitySetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponSettingDao<CouponSetting> extends BaseDao {

    List<CouponSetting> selectCommonOrId(@Param("type") EntitySetting.CouponType type,@Param("id") Long id);
}