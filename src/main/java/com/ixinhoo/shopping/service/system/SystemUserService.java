package com.ixinhoo.shopping.service.system;

import com.google.common.base.Strings;
import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.code.DataTableMeta;
import com.ixinhoo.api.code.DataTableRequest;
import com.ixinhoo.api.constants.UserPasswordEncrypt;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.entity.IdEntity;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.api.shiro.ShiroUserToken;
import com.ixinhoo.api.util.UsersUtil;
import com.ixinhoo.crumbs.code.util.collection.Collections3;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.system.SystemUserDao;
import com.ixinhoo.shopping.dto.system.StoreSystemUserDto;
import com.ixinhoo.shopping.dto.system.SystemUserDto;
import com.ixinhoo.shopping.entity.system.SystemUser;
import com.ixinhoo.shopping.vo.system.SystemUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemUserService extends BaseService {
    @Autowired
    private SystemUserDao<SystemUser> dao;


    @Override
    protected BaseDao<SystemUser> getBaseDao() {
        return dao;
    }

    /**
     * 新增、编译用户
     *
     * @param userDto
     * @return
     */
    @Transactional
    public StatusDto saveSystemUser(SystemUserDto userDto) {
        StatusDto dto = new StatusDto(false);
        if (userDto == null) {
            dto.setMsg("参数不能为空");
        } else {
            SystemUser user = BeanMapper.map(userDto, SystemUser.class);
            if (user.getStatus() == null) {
                user.setStatus(IdEntity.DataStatus.ENABLE);
            }
            SystemUser loginNameUser = dao.selectByLoginName(userDto.getLoginName());
            if (user.getId() == null || user.getId() == 0L) {
                if (loginNameUser != null) {
                    dto.setMsg("登陆账号已存在,请重新输入");
                } else {
                    if (Strings.isNullOrEmpty(user.getPassword())) {
                        user.setPassword("123456");
                    }
                    UserPasswordEncrypt up = UsersUtil.encryptPassword(user.getPassword());
                    user.setPassword(up.getPassword());
                    user.setSalt(up.getSalt());
                    if (userDto.getRoleId() != null && userDto.getRoleId() != 0L) {
                        user.setRoleId(userDto.getRoleId());
                    }
                    super.create(user);
                    dto.setStatus(true);
                }
            } else {
                if (loginNameUser != null && !userDto.getLoginName().equals(loginNameUser.getLoginName())) {
                    dto.setMsg("登陆账号已存在,请重新输入");
                } else {
                    SystemUser dbUser = getBaseDao().selectById(user.getId());
                    if (dbUser == null) {
                        dto.setMsg("用户不存在");
                    } else {
                        if (!Strings.isNullOrEmpty(user.getPassword())) {
                            String pwd = UsersUtil.encryptPasswordBySalt(user.getPassword(), dbUser.getSalt());
                            dbUser.setPassword(pwd);
                        }
                        dbUser.setLoginName(user.getLoginName());
                        dbUser.setName(user.getName());
                        if (userDto.getRoleId() != null && userDto.getRoleId() != 0L) {
                            dbUser.setRoleId(userDto.getRoleId());
                        }
                        super.updateById(dbUser);
                        dto.setStatus(true);
                    }
                }

            }
        }
        return dto;
    }

    public DataTable<SystemUserDto> listSystemUser(DataTableRequest dataTable) {
        DataTable<SystemUserDto> dto = new DataTable<>();
        SystemUserVo vo = new SystemUserVo();
        DataTableMeta meta = new DataTableMeta();
        if (dataTable != null) {
            vo.setCcStart((dataTable.getPagination().getPage() == 0 ? 0 : dataTable.getPagination().getPage() - 1) * dataTable.getPagination().getPerpage());
            vo.setCcEnd(dataTable.getPagination().getPerpage());
            vo.setCcSort(dataTable.getPagination().getSort());
            vo.setCcField(dataTable.getPagination().getField());
            vo.setNameLike(dataTable.getQuery());
            meta.setPerpage(dataTable.getPagination().getPerpage());
            meta.setPages(dataTable.getPagination().getPages());
            meta.setPage(dataTable.getPagination().getPage());
        }
        List<SystemUser> list = getBaseDao().select(vo);
        dto.setData(BeanMapper.mapList(list, SystemUserDto.class));
        Long count = getBaseDao().selectCount(vo);
        meta.setTotal(count == null ? 0 : count.intValue());
        dto.setMeta(meta);
        return dto;
    }

    public DetailDto<SystemUserDto> loginUser(SystemUserDto userDto) {
        DetailDto<SystemUserDto> dto = new DetailDto(false);
        if (userDto == null) {
            dto.setMsg("参数为空");
        } else if (Strings.isNullOrEmpty(userDto.getLoginName())) {
            dto.setMsg("登陆账号为空");
        } else if (Strings.isNullOrEmpty(userDto.getPassword())) {
            dto.setMsg("密码为空");
        } else {
            SystemUser user = dao.selectByLoginName(userDto.getLoginName());
            if (user == null) {
                dto.setMsg("用户不存在");
            } else {
                String password = UsersUtil.encryptPasswordBySalt(userDto.getPassword(), user.getSalt());
                if (!password.equals(user.getPassword())) {
                    dto.setMsg("用户名或密码不正确");
                } else {
                    dto.setStatus(true);
                    dto.setDetail(BeanMapper.map(user, SystemUserDto.class));
                    Subject s = SecurityUtils.getSubject();
                    ShiroUserToken shiroUser = new ShiroUserToken(user.getId(), user.getRoleId(), user.getLoginName(),
                            userDto.getPassword(), user.getName(), "server");
                    s.login(shiroUser);
                }
            }
        }
        return dto;
    }

    public ListDto<StoreSystemUserDto> findStoreManagerByStoreId(Long id) {
        ListDto<StoreSystemUserDto> dto = new ListDto<>();
        dto.setStatus(false);
        if (id == null || id == 0L) {
            dto.setMsg("参数为空");
        } else {
            dto.setStatus(true);
            List<SystemUser> userList = dao.selectByStoreAndRoleId(id, 3);
            if (Collections3.isNotEmpty(userList)) {
                dto.setList(BeanMapper.mapList(userList, StoreSystemUserDto.class));
            }
        }
        return dto;
    }

    @Transactional
    public StatusDto saveStoreManager(Long userId, Long storeId) {
        StatusDto dto = new StatusDto(false);
        if (userId == null || userId == 0L) {
            dto.setMsg("用户id为空");
        } else if (storeId == null || storeId == 0L) {
            dto.setMsg("门店id为空");
        } else {
            SystemUser user = getBaseDao().selectById(userId);
            if (user == null) {
                dto.setMsg("用户不存在");
            } else {
                user.setStoreId(storeId);
                dto.setStatus(true);
                getBaseDao().updateById(user);
            }
        }
        return dto;
    }

    public StatusDto loginStoreUser(String loginName, String password) {
        StatusDto dto = new StatusDto(false);
        if (Strings.isNullOrEmpty(loginName)) {
            dto.setMsg("账号为空");
        } else if (Strings.isNullOrEmpty(password)) {
            dto.setMsg("密码为空");
        } else {
            SystemUser user = dao.selectByLoginName(loginName);
            if (user == null) {
                dto.setMsg("用户不存在");
            } else {
                String pwd = UsersUtil.encryptPasswordBySalt(password, user.getSalt());
                if (!pwd.equals(user.getPassword())) {
                    dto.setMsg("密码不正确");
                } else {
                    if (user.getStoreId() == null) {
                        dto.setMsg("该用户没有门店管理员权限");
                    } else {
                        dto.setStatus(true);
                        dto.setCode(user.getStoreId().toString());
                    }
                }
            }
        }
        return dto;
    }
}