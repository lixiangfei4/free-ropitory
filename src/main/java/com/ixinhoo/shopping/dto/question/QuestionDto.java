package com.ixinhoo.shopping.dto.question;

import com.ixinhoo.api.client.StatusDto;

/**
 * 等级配置
 *
 * @author cici
 */
public class QuestionDto extends StatusDto{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 公告
	 */
	private Long id;
	private Long userId;//标题
    private Long productId;
    private String text;
    private String qName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getqName() {
		return qName;
	}
	public void setqName(String qName) {
		this.qName = qName;
	}
    
}