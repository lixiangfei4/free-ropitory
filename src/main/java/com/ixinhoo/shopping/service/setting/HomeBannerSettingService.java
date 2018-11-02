package com.ixinhoo.shopping.service.setting;

import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.code.DataTableMeta;
import com.ixinhoo.api.code.DataTableRequest;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.shopping.dao.setting.HomeBannerSettingDao;
import com.ixinhoo.shopping.entity.setting.HomeBannerSetting;
import com.ixinhoo.shopping.vo.setting.HomeBannerSettingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HomeBannerSettingService extends BaseService {
    @Autowired
    private HomeBannerSettingDao<HomeBannerSetting> dao;


    @Override
    protected BaseDao<HomeBannerSetting> getBaseDao() {
        return dao;
    }

    @Transactional
    public StatusDto saveHomeBannerSetting(HomeBannerSetting reqDto) {
        StatusDto dto = new StatusDto(false);
        if (reqDto == null) {
            dto.setMsg("参数不能为空");
        } else {
            HomeBannerSetting entity = reqDto;
            if (entity.getId() == null || entity.getId() == 0L) {
                super.create(entity);
                dto.setStatus(true);
            } else {
                HomeBannerSetting dbEntity = getBaseDao().selectById(entity.getId());
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

    public DataTable<HomeBannerSetting> listHomeBannerSetting(DataTableRequest dataTable) {
        DataTable<HomeBannerSetting> dto = new DataTable<>();
        HomeBannerSettingVo vo = new HomeBannerSettingVo();
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
        List<HomeBannerSetting> list = getBaseDao().select(vo);
        dto.setData(list);
        Long count = getBaseDao().selectCount(vo);
        meta.setTotal(count == null ? 0 : count.intValue());
        dto.setMeta(meta);
        return dto;

    }
}