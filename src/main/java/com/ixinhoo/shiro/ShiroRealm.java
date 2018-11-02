package com.ixinhoo.shiro;

import com.ixinhoo.api.entity.ApiSetting;
import com.ixinhoo.api.shiro.ShiroUser;
import com.ixinhoo.api.shiro.ShiroUserToken;
import com.ixinhoo.crumbs.code.security.utils.Digests;
import com.ixinhoo.crumbs.code.util.key.Encodes;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 认证回调函数,登录时调用.
     * 用户认证方法.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("doGetAuthorizationInfo:{}", token);
        ShiroUserToken user = (ShiroUserToken) token;
        byte[] salt = Encodes.decodeHex("7efbd59d9741d34f");
        byte[] hashPassword = Digests.sha1(String.valueOf(user.getPassword()).getBytes(), salt, ApiSetting.HASH_INTERATIONS);
        return new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getRoleId(), user.getUsername(), user.getName(),
                user.getSystem()), hashPassword, ByteSource.Util.bytes(salt), getName());
    }


    //添加清除缓存配置,使用重试数据匹配器进行
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }


    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     * 访问链接时的授权方法.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Long roleId = shiroUser.getRoleId();
        if (roleId != null && roleId != 0L) {
            if (roleId == 1L) {
                info.addRole("admin");
            } else if (roleId == 2L) {
                info.addRole("general");
            } else if (roleId == 3L) {
                info.addRole("custom");
            }
        }
        return info;
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }


}
