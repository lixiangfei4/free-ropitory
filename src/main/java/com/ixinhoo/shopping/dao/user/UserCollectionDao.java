package com.ixinhoo.shopping.dao.user;

import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.shopping.entity.EntitySetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCollectionDao<UserCollection> extends BaseDao {

    UserCollection selectByUserIdAndDataIdAndType(@Param("userId") Long userId,
                                                  @Param("dataId") Long dataId,
                                                  @Param("type") EntitySetting.UserCollectionType type);

    int deleteByUserIdAndDataIdAndType(@Param("userId") Long userId,
                                       @Param("dataId") Long dataId,
                                       @Param("type") EntitySetting.UserCollectionType type);

    List<UserCollection> selectByUserId(@Param("userId") Long userId,
                                        @Param("type") EntitySetting.UserCollectionType type,
                                        @Param("ccStart") Integer ccStart,
                                        @Param("ccEnd") Integer ccEnd);
}