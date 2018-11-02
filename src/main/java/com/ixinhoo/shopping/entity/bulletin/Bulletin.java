package com.ixinhoo.shopping.entity.bulletin;

import com.ixinhoo.api.entity.AuditEntity;

/**
 * 等级配置
 *
 * @author cici
 */
public class Bulletin extends AuditEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 公告
	 */
	private Long id;
	private String btitle;//标题
    private Integer status;
    private String description;
    private String url;
    private Integer sort;
    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
   
}