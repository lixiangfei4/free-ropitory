package com.ixinhoo.shopping.dao.user;

import com.ixinhoo.api.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserIntegralDao<UserSignIntegral> extends BaseDao {

    List<UserSignIntegral> selectByUserIdAndTime(@Param("userId") Long userId,@Param("beginTime") Long beginTime, @Param("endTime")Long endTime);

    Long selectSumIntegralByUserId(Long userId);
}