package com.ixinhoo.shopping.entity.system;

import com.ixinhoo.api.entity.AuditEntity;

import java.beans.Transient;

/**
 * 系统后台用户表
 *
 * @author cici
 */
public class SystemUser extends AuditEntity {
    private String loginName;//登录账号
    private String password;//登录密码
    private String salt;
    private String name;//用户名
    private DataStatus status;//状态  （启用  与未启用）
    private Long roleId;
    private String plainPassword;
    private Long storeId;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataStatus getStatus() {
        return status;
    }

    public void setStatus(DataStatus status) {
        this.status = status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Transient
    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }


}