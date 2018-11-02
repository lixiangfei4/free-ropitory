package com.ixinhoo.shopping.dao.user;

import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.shopping.entity.EntitySetting;
import org.apache.ibatis.annotations.Param;

public interface MessageCodeDao<MessageCode> extends BaseDao {
    MessageCode selectByPhoneAndTime(@Param("phone")String phone,
                                     @Param("invalidTime") Long invalidTime,
                                     @Param("type")EntitySetting.MessageCodeType type);
}