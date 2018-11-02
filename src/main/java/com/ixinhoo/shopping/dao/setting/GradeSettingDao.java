package com.ixinhoo.shopping.dao.setting;

import com.ixinhoo.api.dao.BaseDao;

public interface GradeSettingDao<GradeSetting> extends BaseDao {

    GradeSetting selectByIntegral(Long integral);
}