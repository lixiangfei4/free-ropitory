package com.ixinhoo.shopping.dao.user;

import com.ixinhoo.api.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MobileUserDao<MobileUser> extends BaseDao{

    MobileUser selectByPhone(String phone);

    List<MobileUser> selectByOpenid(String openid);

    List<MobileUser> selectByIds(@Param("ids") List<Long> ids);
}