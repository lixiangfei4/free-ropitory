package com.ixinhoo.shopping.dto.user;

/**
 * "integral": 1, //积分数目
 * "nextIntegral": 100, //下一个级别积分
 * "days": 0,//连续签到天数
 * "signed": true,//是否已签到
 * "signIntegral": 1,//签到获取到的积分
 * "nextName":"金牌会员"
 */
public class UserGradeDto {
    private Integer integral;
    private Integer nextIntegral;
    private Integer days;
    private Boolean signed;
    private Long signIntegral;
    private String nextName;

    public String getNextName() {
        return nextName;
    }

    public void setNextName(String nextName) {
        this.nextName = nextName;
    }

    public Long getSignIntegral() {
        return signIntegral;
    }

    public void setSignIntegral(Long signIntegral) {
        this.signIntegral = signIntegral;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getNextIntegral() {
        return nextIntegral;
    }

    public void setNextIntegral(Integer nextIntegral) {
        this.nextIntegral = nextIntegral;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }
}