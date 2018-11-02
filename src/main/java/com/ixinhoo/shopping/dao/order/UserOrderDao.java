package com.ixinhoo.shopping.dao.order;

import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.shopping.entity.EntitySetting;
import org.apache.ibatis.annotations.Param;

public interface UserOrderDao<UserOrder> extends BaseDao {

    Long selectCountByAddressIdAndStatusAndType(@Param("addressId") Long addressId,
                                              @Param("orderStatus") EntitySetting.OrderStatus status,
                                          @Param("buyType") EntitySetting.BuyProductType buyType);
}