package com.ixinhoo.shopping.service.user;

import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.store.StoreDao;
import com.ixinhoo.shopping.dao.user.UserEvaluateDao;
import com.ixinhoo.shopping.dto.user.UserEvaluateDto;
import com.ixinhoo.shopping.entity.store.Store;
import com.ixinhoo.shopping.entity.user.UserEvaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserEvaluateService extends BaseService {
    @Autowired
    private UserEvaluateDao dao;
    @Autowired
    private StoreDao storeDao;

    @Override
    protected BaseDao<UserEvaluate> getBaseDao() {
        return dao;
    }

    @Transactional
    public StatusDto saveUserEvaluate(UserEvaluateDto reqDto) {
        StatusDto dto = new StatusDto(false);
        if (reqDto == null) {
            dto.setMsg("数据为空");
        } else if (reqDto.getUserId() == null || reqDto.getUserId() == 0L) {
            dto.setMsg("用户为空");
        } else if (reqDto.getDataId() == null || reqDto.getDataId() == 0L) {
            dto.setMsg("店铺id为空");
        } else {
            Store store = (Store) storeDao.selectById(reqDto.getDataId());
            if (store == null) {
                dto.setMsg("店铺不存在");
            } else {
                UserEvaluate entity = BeanMapper.map(reqDto, UserEvaluate.class);
                entity.setTime(System.currentTimeMillis());
                super.create(entity);
                Double storeScore = store.getScore();
                if (storeScore == null) {
                    store.setScore(reqDto.getScore());
                } else {
                    store.setScore(storeScore + (reqDto.getScore() == null ? 0 : reqDto.getScore()));
                }
                storeDao.updateById(store);
                dto.setStatus(true);
            }
        }
        return dto;
    }
}