package com.ixinhoo.shopping.service.setting;

import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.code.DataTableMeta;
import com.ixinhoo.api.code.DataTableRequest;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.setting.GradeSettingDao;
import com.ixinhoo.shopping.dto.setting.GradeSettingDto;
import com.ixinhoo.shopping.entity.setting.GradeSetting;
import com.ixinhoo.shopping.vo.setting.GradeSettingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GradeSettingService extends BaseService {
    @Autowired
    private GradeSettingDao<GradeSetting> dao;


    @Override
    protected BaseDao<GradeSetting> getBaseDao() {
        return dao;
    }

    @Transactional
    public StatusDto saveGradeSetting(GradeSettingDto reqDto) {
        StatusDto dto = new StatusDto(false);
        if (reqDto == null) {
            dto.setMsg("参数不能为空");
        } else {
            GradeSetting entity = BeanMapper.map(reqDto, GradeSetting.class);
            if (entity.getId() == null || entity.getId() == 0L) {
                super.create(entity);
                dto.setStatus(true);
            } else {
                GradeSetting dbEntity = getBaseDao().selectById(entity.getId());
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

    public DataTable<GradeSettingDto> listGradeSetting(DataTableRequest dataTable) {
        DataTable<GradeSettingDto> dto = new DataTable<>();
        GradeSettingVo vo = new GradeSettingVo();
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
        List<GradeSetting> list = getBaseDao().select(vo);
        dto.setData(BeanMapper.mapList(list, GradeSettingDto.class));
        Long count = getBaseDao().selectCount(vo);
        meta.setTotal(count == null ? 0 : count.intValue());
        dto.setMeta(meta);
        return dto;

    }
}