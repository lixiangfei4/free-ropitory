package com.ixinhoo.shopping.dao.user;

import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.shopping.entity.EntitySetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAddressDao<UserAddress> extends BaseDao {

    List<UserAddress> selectByUserId(@Param("userId") Long userId);

    int updateDefault(@Param("isDefault") EntitySetting.YesOrNoType isDefault, @Param("id") Long id);

    List<UserAddress> selectByUserIdAndDefault(@Param("userId") Long userId, @Param("isDefault") EntitySetting.YesOrNoType isDefault);

}