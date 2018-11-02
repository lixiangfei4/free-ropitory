package com.ixinhoo.shopping.controller.indexController;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.code.DataTableRequest;
import com.ixinhoo.api.util.DataTableUtil;
import com.ixinhoo.shopping.entity.indexService.IndexService;
import com.ixinhoo.shopping.service.indexService.IndexServiceService;

@RestController
@RequestMapping(value = "/api/bg/service")
public class IndexServiceController {

    @Autowired
    private IndexServiceService indexServiceService;
	/**
	 * 插入一条数据
	 * @param service
	 * @return
	 */

    @RequestMapping(value = "insert",method=RequestMethod.POST)
    public StatusDto indexService(IndexService service) {
    	StatusDto ss = indexServiceService.insert(service);
    	return ss;
		
    }
	/**
	 * 更新一条数据
	 * @param service
	 * @return
	 */

    @RequestMapping(value = "update",method=RequestMethod.POST)
    public StatusDto updateServiceById(IndexService service) {
    	StatusDto ss = indexServiceService.updateById(service);
    	return ss;
		
    }
  
    /**
	 * 删除一条数据
	 * @param service
	 * @return
	 */

    @RequestMapping(value = "delete",method=RequestMethod.POST)
    public StatusDto deleteServiceById(Long[] id) {
    	return indexServiceService.deleteByIds(id);
    	
		
    }
    /**
     * 查询全部数据
     * @param service
     * @return
     */

   @RequestMapping(value = "select",method=RequestMethod.POST)
    public DataTable<IndexService> find(HttpServletRequest request) {
    	return indexServiceService.listIndexService(DataTableUtil.toDataTable(request));
		
    }
}