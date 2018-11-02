package com.ixinhoo.shopping.service.system;

import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.shopping.dao.system.SystemRoleDao;
import com.ixinhoo.shopping.dao.system.SystemUserDao;
import com.ixinhoo.shopping.entity.system.SystemRole;
import com.ixinhoo.shopping.entity.system.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemRoleService extends BaseService {
    @Autowired
	private SystemRoleDao<SystemRole> dao;

	@Override
	protected BaseDao<SystemRole> getBaseDao() {
		return dao;
	}


}