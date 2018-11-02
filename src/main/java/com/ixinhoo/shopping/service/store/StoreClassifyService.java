package com.ixinhoo.shopping.service.store;

import com.google.common.base.Strings;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.code.DataTableMeta;
import com.ixinhoo.api.code.DataTableRequest;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.store.StoreClassifyDao;
import com.ixinhoo.shopping.dto.store.StoreClassifyDto;
import com.ixinhoo.shopping.entity.store.StoreClassify;
import com.ixinhoo.shopping.vo.store.StoreClassifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreClassifyService extends BaseService {
    @Autowired
    private StoreClassifyDao<StoreClassify> dao;


    @Override
    protected BaseDao<StoreClassify> getBaseDao() {
        return dao;
    }

    @Transactional
    public StatusDto saveStoreClassify(StoreClassify reqDto) {
        StatusDto dto = new StatusDto(false);
        if (reqDto == null) {
            dto.setMsg("参数不能为空");
        } else {
            StoreClassify entity = reqDto;
            if (entity.getId() == null || entity.getId() == 0L) {
                super.create(entity);
                dto.setStatus(true);
            } else {
                StoreClassify dbEntity = getBaseDao().selectById(entity.getId());
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

    public DataTable<StoreClassify> listStoreClassify(DataTableRequest dataTable) {
        DataTable<StoreClassify> dto = new DataTable<>();
        StoreClassifyVo vo = new StoreClassifyVo();
        DataTableMeta meta = new DataTableMeta();
        if (dataTable != null) {
            vo.setCcStart((dataTable.getPagination().getPage() == 0 ? 0 : dataTable.getPagination().getPage() - 1) * dataTable.getPagination().getPerpage());
            vo.setCcEnd(dataTable.getPagination().getPerpage());
            vo.setCcSort(dataTable.getPagination().getSort());
            vo.setCcField(dataTable.getPagination().getField());
            if(!Strings.isNullOrEmpty(dataTable.getQuery())){
                vo.setNameLike(dataTable.getQuery());
            }
            meta.setPerpage(dataTable.getPagination().getPerpage());
            meta.setPages(dataTable.getPagination().getPages());
            meta.setPage(dataTable.getPagination().getPage());
        }
        List<StoreClassify> list = getBaseDao().select(vo);
        dto.setData(list);
        Long count = getBaseDao().selectCount(vo);
        meta.setTotal(count == null ? 0 : count.intValue());
        dto.setMeta(meta);
        return dto;

    }

    public ListDto<StoreClassifyDto> findStoreClassify(Integer p, Integer s) {
        ListDto<StoreClassifyDto> dto = new ListDto<>();
        dto.setStatus(true);
        StoreClassifyVo vo = new StoreClassifyVo();
        if (p != null && s != null) {
            vo.setCcStart((p == 0 ? 0 : p - 1) * s);
            vo.setCcEnd(s);
        }
        List<StoreClassify> list = getBaseDao().select(vo);
        dto.setList(BeanMapper.mapList(list, StoreClassifyDto.class));
        return dto;
    }

    public ListDto<StoreClassifyDto> findAllStoreClassify() {
        ListDto<StoreClassifyDto> dto = new ListDto<>();
        dto.setStatus(true);
        List<StoreClassify> list = getBaseDao().select(null);
        dto.setList(BeanMapper.mapList(list, StoreClassifyDto.class));
        return dto;
    }
}