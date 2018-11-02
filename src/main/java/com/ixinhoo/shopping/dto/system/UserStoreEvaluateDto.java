package com.ixinhoo.shopping.dto.system;

/**
 * 用户评价DTO
 *
 * @author 448778074@qq.com (cici)
 */
public class UserStoreEvaluateDto {
    private String image;//头像
    private String nickName;//昵称
    private Long time;//时间
    private String content;//内容

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
