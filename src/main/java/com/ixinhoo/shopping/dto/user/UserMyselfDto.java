package com.ixinhoo.shopping.dto.user;

/**
 * 用户中心DTO
 */
public class UserMyselfDto {
    private Long id;//用户主键
    private String image;//头像
    private String nickName;//昵称
    private String openid;//微信openid
    private Integer integral;//用户积分
    private Long gradeId;//等级id
    private String gradeName;//等级名称
    private Integer takingSum;//待提货总数
    private Integer takenSum;//已提货总数

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getTakingSum() {
        return takingSum;
    }

    public void setTakingSum(Integer takingSum) {
        this.takingSum = takingSum;
    }

    public Integer getTakenSum() {
        return takenSum;
    }

    public void setTakenSum(Integer takenSum) {
        this.takenSum = takenSum;
    }
}