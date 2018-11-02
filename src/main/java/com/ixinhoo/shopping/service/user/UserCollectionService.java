package com.ixinhoo.shopping.service.user;

import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.crumbs.code.util.collection.Collections3;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.store.ProductDao;
import com.ixinhoo.shopping.dao.user.UserCollectionDao;
import com.ixinhoo.shopping.dto.store.ProductSimpleDto;
import com.ixinhoo.shopping.entity.EntitySetting;
import com.ixinhoo.shopping.entity.store.Product;
import com.ixinhoo.shopping.entity.user.UserCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCollectionService extends BaseService {
    @Autowired
    private UserCollectionDao<UserCollection> userCollectionDao;
    @Autowired
    private ProductDao productDao;

    @Override
    protected BaseDao<UserCollection> getBaseDao() {
        return userCollectionDao;
    }

    @Transactional
    public StatusDto saveUserCollectionByUserId(Long userId, Long dataId) {
        StatusDto dto = new StatusDto(false);
        if (userId == null || userId == 0L) {
            dto.setMsg("用户参数为空");
        } else if (dataId == null || dataId == 0L) {
            dto.setMsg("数据参数为空");
        } else {
            Product product = (Product) productDao.selectById(dataId);
            if (product == null) {
                dto.setMsg("商品不存在");
            } else {
                dto.setStatus(true);
                UserCollection userCollection = userCollectionDao.selectByUserIdAndDataIdAndType(userId, dataId, EntitySetting.UserCollectionType.PRODUCT);
                if (userCollection == null) {
                    userCollection = new UserCollection();
                    userCollection.setUserId(userId);
                    userCollection.setDataId(dataId);
                    userCollection.setTime(System.currentTimeMillis());
                    userCollection.setType(EntitySetting.UserCollectionType.PRODUCT);
                    super.create(userCollection);
                    dto.setCode("collection");
                    dto.setMsg("收藏成功");
                }else{
                    super.deleteById(userCollection.getId());
                    dto.setCode("cancel");
                    dto.setMsg("取消收藏成功");
                }
            }
        }
        return dto;
    }

    @Transactional
    public StatusDto deleteUserCollectionByUserId(Long userId, Long dataId) {
        StatusDto dto = new StatusDto(false);
        if (userId == null || userId == 0L) {
            dto.setMsg("用户参数为空");
        } else if (dataId == null || dataId == 0L) {
            dto.setMsg("数据参数为空");
        } else {
            dto.setStatus(true);
            userCollectionDao.deleteByUserIdAndDataIdAndType(userId, dataId, EntitySetting.UserCollectionType.PRODUCT);
        }
        return dto;
    }

    public ListDto<ProductSimpleDto> pageUserCollection(Long userId, Integer p, Integer s) {
        ListDto<ProductSimpleDto> dto = new ListDto<>();
        dto.setStatus(false);
        if (userId == null || userId == 0L) {
            dto.setMsg("用户不存在");
        } else {
            dto.setStatus(true);
            s = s == null || s == 0L ? Integer.MAX_VALUE : s;
            p = p == null ? 0 : p * s;
            List<UserCollection> userCollectionList = userCollectionDao.selectByUserId(userId, EntitySetting.UserCollectionType.PRODUCT, p, s);
            if (Collections3.isNotEmpty(userCollectionList)) {
                List<Long> ids = userCollectionList.stream().map(d -> d.getDataId()).collect(Collectors.toList());
                List<Product> products = productDao.selectByIds(ids);
                dto.setList(BeanMapper.mapList(products, ProductSimpleDto.class));
            }
        }
        return dto;
    }
}