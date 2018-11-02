package com.ixinhoo.shopping.service.user;

import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.shopping.dao.user.UserAddressDao;
import com.ixinhoo.shopping.entity.EntitySetting;
import com.ixinhoo.shopping.entity.user.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAddressService extends BaseService {
    @Autowired
    private UserAddressDao<UserAddress> dao;


    @Override
    protected BaseDao<UserAddress> getBaseDao() {
        return dao;
    }

    @Transactional
    public StatusDto saveUserAddress(UserAddress reqDto) {
        StatusDto dto = new StatusDto(false);
        if (reqDto == null) {
            dto.setMsg("参数不能为空");
        } else {
            UserAddress entity = reqDto;
            if (entity.getId() == null || entity.getId() == 0L) {
                super.create(entity);
                dto.setStatus(true);
            } else {
                UserAddress dbEntity = getBaseDao().selectById(entity.getId());
                if (dbEntity == null) {
                    dto.setMsg("实体不存在");
                } else {
                    super.updateById(entity);
                    dto.setStatus(true);
                }
            }
        }
        return dto;
    }

    public ListDto<UserAddress> listByUserId(Long userId) {
        ListDto<UserAddress> dto = new ListDto<>();
        dto.setStatus(false);
        if (userId == null || userId == 0L) {
            dto.setMsg("用户主键为空");
        } else {
            dto.setStatus(true);
            dto.setList(dao.selectByUserId(userId));
        }
        return dto;
    }

    @Transactional
    public StatusDto defaultAddressById(Long id) {
        StatusDto dto = new StatusDto(false);
        if (id == null || id == 0L) {
            dto.setMsg("主键为空");
        } else {
            dao.updateDefault(EntitySetting.YesOrNoType.NO,null);
            dao.updateDefault(EntitySetting.YesOrNoType.YES,id);
            dto.setStatus(true);
        }
        return dto;
    }
}