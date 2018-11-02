package com.ixinhoo.shopping.dto.system;

import com.ixinhoo.api.entity.IdEntity;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统用户dto
 * @author 448778074@qq.com (cici)
 */
public class SystemUserDto {
    private Long id;
    private String loginName;//登录账号
    private String password;//登录密码
    private String name;//用户名
    private IdEntity.DataStatus status;//状态  （启用  与未启用）
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }
    @NotBlank
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdEntity.DataStatus getStatus() {
        return status;
    }

    public void setStatus(IdEntity.DataStatus status) {
        this.status = status;
    }
}
