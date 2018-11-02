package com.ixinhoo.shopping.dao.order;

import com.ixinhoo.api.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOrderAddressDao<UserOrderAddress> extends BaseDao {

    List<UserOrderAddress> selectByOrderIds(@Param("ids") List<Long> ids);
}