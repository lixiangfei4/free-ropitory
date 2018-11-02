package com.ixinhoo.shopping.dao.system;

import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.shopping.entity.system.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemUserDao<SystemUser> extends BaseDao{
    SystemUser selectByLoginName(String loginName);

    List<SystemUser> selectByStoreAndRoleId(@Param("storeId") Long id,
                                            @Param("roleId") Integer roleId);
}