package com.ixinhoo.shopping.dao.order;

import com.ixinhoo.api.dao.BaseDao;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserOrderProductDao<UserOrderProduct> extends BaseDao {

    List<UserOrderProduct> selectByOrderIds(@RequestParam("list") List<Long> ids);
}