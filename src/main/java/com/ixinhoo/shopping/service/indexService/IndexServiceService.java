package com.ixinhoo.shopping.service.indexService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.code.DataTableMeta;
import com.ixinhoo.api.code.DataTableRequest;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.indexService.IndexServiceMapper;
import com.ixinhoo.shopping.dto.bulletin.BulletinDto;
import com.ixinhoo.shopping.entity.bulletin.Bulletin;
import com.ixinhoo.shopping.entity.indexService.IndexService;
import com.ixinhoo.shopping.vo.bulletin.BulletinVo;

import tk.mybatis.mapper.entity.Example;

@Service
public class IndexServiceService {
    @Autowired
    private IndexServiceMapper dao;

    /**
     * 插入一条服务
     * @return
     */
    @Transactional
    public StatusDto insert(IndexService service) {
    	StatusDto dto = new StatusDto(false);
    	if(service==null) {
    		dto.setMsg("参数不能为空");
    	}else {
    		dao.insert(service);
    		dto.setStatus(true);
    	    return dto;
    	}
		return dto;
		
    }
    /**
     * 根据id更新
     * @param service
     * @return
     */
    @Transactional
    public StatusDto updateById(IndexService service) {
    	StatusDto dto = new StatusDto(false);
    	if(service==null) {
    		dto.setMsg("参数不能为空");
    	}else {
    		service.setCreateTime(new Date().getTime());
    		//选择性全行更新
    		dao.updateByPrimaryKeySelective(service);
    		dto.setStatus(true);
    		return dto;
    	}
    	return dto;
	}
    /**
     * 根据多个id批量删除
     * @param service
     * @return
     */
    @Transactional
    public StatusDto deleteByIds(Long[] id) {
    	List<Long> list = new ArrayList<Long>();
    	//把id加到集合中
    	for(Long i:id) {
    		list.add(i);
    	}
    	StatusDto dto = new StatusDto(false);
    	if(id==null) {
    		dto.setMsg("参数不能为空");
    	}else {
    		Example example = new Example(IndexService.class);
    		//批量删除
    		example.createCriteria().andIn("id", list);
    		//选择性delete
    		dao.deleteByExample(example);
    		dto.setStatus(true);
    		return dto;
    	}
    	return dto;
	}
    
    /**
     * 查询所有
     * @param dataTable
     * @return
     */
    @Transactional
    public DataTable<IndexService> listIndexService(DataTableRequest dataTable) {
    	DataTable<IndexService> dto = new DataTable<>();
    	 DataTableMeta meta = new DataTableMeta();
    	 if (dataTable != null) {
             meta.setPerpage(dataTable.getPagination().getPerpage());
             meta.setPages(dataTable.getPagination().getPages());
             meta.setPage(dataTable.getPagination().getPage());
         }
         List<IndexService> list = dao.select(null);
         dto.setData(list);
         Long count = new Long(list.size());
         meta.setTotal(count == null ? 0 : count.intValue());
         dto.setMeta(meta);
         return dto;
    }
}


   
  