package com.ixinhoo.shopping.service.bulletin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.code.DataTableMeta;
import com.ixinhoo.api.code.DataTableRequest;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.bulletin.BulletinDao;
import com.ixinhoo.shopping.dto.bulletin.BulletinDto;
import com.ixinhoo.shopping.entity.bulletin.Bulletin;
import com.ixinhoo.shopping.vo.bulletin.BulletinVo;

@Service
public class BulletinService extends BaseService {
	
    @Autowired
    private BulletinDao<Bulletin> dao;


    @Override
    protected BaseDao<Bulletin> getBaseDao() {
        return dao;
    }
    
    @Transactional
    public DataTable<BulletinDto> listBulletin(DataTableRequest dataTable) {
    	DataTable<BulletinDto> dto = new DataTable<>();
    	 DataTableMeta meta = new DataTableMeta();
    	 BulletinVo vo = new BulletinVo();
    	 if (dataTable != null) {
             vo.setCcStart((dataTable.getPagination().getPage() == 0 ? 0 : dataTable.getPagination().getPage() - 1) * dataTable.getPagination().getPerpage());
             vo.setCcEnd(dataTable.getPagination().getPerpage());
             vo.setCcSort(dataTable.getPagination().getSort());
             vo.setCcField(dataTable.getPagination().getField());
             meta.setPerpage(dataTable.getPagination().getPerpage());
             meta.setPages(dataTable.getPagination().getPages());
             meta.setPage(dataTable.getPagination().getPage());
         }
         List<Bulletin> list = getBaseDao().select(vo);
         List<BulletinDto> dtos = BeanMapper.mapList(list, BulletinDto.class);
          dto.setData(dtos);
         Long count = getBaseDao().selectCount(vo);
         meta.setTotal(count == null ? 0 : count.intValue());
         dto.setMeta(meta);
         return dto;
    }
    @Transactional
	public StatusDto saveBulletin(BulletinDto reqDto) {
		StatusDto dto = new StatusDto(false);
	   if (reqDto == null) {
            dto.setMsg("参数不能为空");
        } else {
            Bulletin entity = BeanMapper.map(reqDto, Bulletin.class);
            if (entity.getId() == null || entity.getId() == 0L) {
                super.create(entity);
                dto.setStatus(true);
            } else {
            	Bulletin dbEntity = getBaseDao().selectById(entity.getId());
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
   }