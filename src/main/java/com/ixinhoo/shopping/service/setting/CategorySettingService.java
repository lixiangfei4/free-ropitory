package com.ixinhoo.shopping.service.setting;

import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.code.DataTableMeta;
import com.ixinhoo.api.code.DataTableRequest;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.shopping.dao.setting.CategorySettingDao;
import com.ixinhoo.shopping.entity.setting.CategorySetting;
import com.ixinhoo.shopping.vo.setting.CategorySettingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategorySettingService extends BaseService {
    @Autowired
    private CategorySettingDao<CategorySetting> dao;


    @Override
    protected BaseDao<CategorySetting> getBaseDao() {
        return dao;
    }

    @Transactional
    public StatusDto saveCategorySetting(CategorySetting reqDto) {
        StatusDto dto = new StatusDto(false);
        if (reqDto == null) {
            dto.setMsg("参数不能为空");
        } else {
            CategorySetting entity = reqDto;
            if (entity.getId() == null || entity.getId() == 0L) {
                super.create(entity);
                dto.setStatus(true);
            } else {
                CategorySetting dbEntity = getBaseDao().selectById(entity.getId());
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

    public DataTable<CategorySetting> listCategorySetting(DataTableRequest dataTable) {
        DataTable<CategorySetting> dto = new DataTable<>();
        CategorySettingVo vo = new CategorySettingVo();
        DataTableMeta meta = new DataTableMeta();
        if (dataTable != null) {
            vo.setCcStart((dataTable.getPagination().getPage() == 0 ? 0 : dataTable.getPagination().getPage() - 1) * dataTable.getPagination().getPerpage());
            vo.setCcEnd(dataTable.getPagination().getPerpage());
            vo.setCcSort(dataTable.getPagination().getSort());
            vo.setCcField(dataTable.getPagination().getField());
            meta.setPerpage(dataTable.getPagination().getPerpage());
            meta.setPages(dataTable.getPagination().getPages());
            meta.setPage(dataTable.getPagination().getPage());
        }
        List<CategorySetting> list = getBaseDao().select(vo);
        dto.setData(list);
        Long count = getBaseDao().selectCount(vo);
        meta.setTotal(count == null ? 0 : count.intValue());
        dto.setMeta(meta);
        return dto;

    }
}