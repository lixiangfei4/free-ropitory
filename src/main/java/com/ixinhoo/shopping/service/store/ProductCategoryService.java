package com.ixinhoo.shopping.service.store;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.crumbs.code.util.collection.Collections3;
import com.ixinhoo.shopping.dao.setting.CategorySettingDao;
import com.ixinhoo.shopping.dao.store.ProductCategoryDao;
import com.ixinhoo.shopping.entity.setting.CategorySetting;
import com.ixinhoo.shopping.entity.store.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProductCategoryService extends BaseService {
    @Autowired
    private ProductCategoryDao<ProductCategory> dao;
    @Autowired
    private CategorySettingDao<CategorySetting> categorySettingDao;


    @Override
    protected BaseDao<ProductCategory> getBaseDao() {
        return dao;
    }

    @Transactional
    public StatusDto saveProductCategory(List<ProductCategory> list) {
        StatusDto dto = new StatusDto(false);
        if (Collections3.isEmpty(list)) {
            dto.setMsg("参数不能为空");
        } else {
            //删除重新插入
            dao.deleteByProductId(list.get(0).getProductId());
            for (ProductCategory e : list) {
                dao.insert(e);
            }
            dto.setStatus(true);
        }
        return dto;
    }


    public ListDto<ProductCategory> findProductCategoryById(Long id) {
        ListDto<ProductCategory> dto = new ListDto<>();
        dto.setStatus(false);
        if (id == null || id == 0L) {
            dto.setMsg("参数为空");
        } else {
            //查询所有的规则
            List<CategorySetting> list = categorySettingDao.select(null);
            if (Collections3.isEmpty(list)) {
                dto.setMsg("规则配置为空");
            } else {
                dto.setStatus(true);
                //查询该商品类型下的所有规格
                List<ProductCategory> categories = dao.selectByProductId(id);
                List<ProductCategory> dtoList = Lists.newArrayList();
                Map<Long, String> names = Maps.newHashMap();
                if (Collections3.isNotEmpty(categories)) {
                    categories.forEach((d) -> {
                        names.put(d.getCategoryId(), d.getName());
                    });
                }
                list.forEach((d) -> {
                    ProductCategory pc = new ProductCategory();
                    pc.setCategoryId(d.getId());
                    pc.setCategoryName(d.getName());
                    pc.setName(names.get(d.getId()));
                    pc.setProductId(id);
                    dtoList.add(pc);
                });
                dto.setList(dtoList);
            }
        }
        return dto;
    }
}