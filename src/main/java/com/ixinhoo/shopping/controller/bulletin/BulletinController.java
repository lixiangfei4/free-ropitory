package com.ixinhoo.shopping.controller.bulletin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.shiro.annotation.ProhibitedEntry;
import com.ixinhoo.api.util.DataTableUtil;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dto.bulletin.BulletinDto;
import com.ixinhoo.shopping.entity.bulletin.Bulletin;
import com.ixinhoo.shopping.service.bulletin.BulletinService;

@RestController
@RequestMapping(value = "/api/bg/bulletin")
public class BulletinController{

	@Autowired
	private BulletinService bulletinService;
	/**
	 * 获取bulletin列表
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<BulletinDto> listBulletin(HttpServletRequest request) {
    	return bulletinService.listBulletin(DataTableUtil.toDataTable(request));
    }
    /**
	 * 插入公告
	 * @param request
	 * @return
	 */
   @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(BulletinDto reqDto) {
        return bulletinService.saveBulletin(reqDto);
    }
   /**
	 * 删除公告
	 * @param request
	 * @return
	 */
   @RequestMapping(value = "delete", method = RequestMethod.POST)
   public StatusDto delete(@RequestParam("ids") List<Long> ids) {
	   bulletinService.deleteByIds(ids);
       return new StatusDto(true);
   }
   /**
 	 * 更新公告
 	 * @param request
 	 * @return
 	 */
   @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
   public StatusDto updateById(BulletinDto bulletinDto) {
	   Bulletin bulletin = BeanMapper.map(bulletinDto, Bulletin.class);
	  bulletinService.updateById(bulletin);
	   return new StatusDto(true);
   }
}
