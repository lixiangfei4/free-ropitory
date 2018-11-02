package com.ixinhoo.shopping.entity.system;

import com.ixinhoo.api.entity.AuditEntity;

/**
 * 系统角色表
 *
 * @author cici
 */
public class SystemRole extends AuditEntity {
    private String name;//角色名称
    private String code;//角色代号
    private String description;//角色描述
    private DataStatus status;//状态
    public SystemRole(){}
    public SystemRole(Long id){this.id=id;}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataStatus getStatus() {
        return status;
    }

    public void setStatus(DataStatus status) {
        this.status = status;
    }
}
