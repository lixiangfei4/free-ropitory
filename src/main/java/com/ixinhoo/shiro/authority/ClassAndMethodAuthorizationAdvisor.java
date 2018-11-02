package com.ixinhoo.shiro.authority;

import com.ixinhoo.api.controller.BaseController;
import com.ixinhoo.api.shiro.annotation.ProhibitedEntry;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 自定义的注解权限AOP扫描 ;
 * 类和方法都适用,修改spring-shiro中的权限注解,AuthorizationAttributeSourceAdvisor.
 *
 * @author 448778074@qq.com (cici)
 */
public class ClassAndMethodAuthorizationAdvisor extends AuthorizationAttributeSourceAdvisor {
    //权限注解
    private static final Class<? extends Annotation>[] AUTHZ_ANNOTATION_CLASSES =
            new Class[]{
                    ProhibitedEntry.class,
                    RequiresPermissions.class, RequiresRoles.class,
                    RequiresUser.class, RequiresGuest.class, RequiresAuthentication.class
            };

    //web注解
    private static final Class<? extends Annotation>[] WEB_ANNOTATION_CLASSES =
            new Class[]{
                    RequestMapping.class
            };


    /**
     * Create a new AuthorizationAttributeSourceAdvisor.
     */
    public ClassAndMethodAuthorizationAdvisor() {
        setAdvice(new ClassAndMethodAnnotationsAuthorizingMethodInterceptor());
    }


    /**
     * 匹配带有注解的方法
     */
    @Override
    public boolean matches(Method method, Class targetClass) {
        boolean flag = super.matches(method, targetClass);

        //如果方法上没有权限注解，尝试获取类上的默认权限注解
        if (!flag && isAuthzAnnotationPresent(targetClass) && isWebAnnotationPresent(method)) {
            flag = true;
        }

        return flag;
    }

    /**
     * 查看BaseController的子类是否有权限注解
     *
     * @param clazz
     * @return
     */
    private boolean isAuthzAnnotationPresent(Class<BaseController> clazz) {
        for (Class<? extends Annotation> annClass : AUTHZ_ANNOTATION_CLASSES) {
            Annotation a = AnnotationUtils.findAnnotation(clazz, annClass);
            if (a != null) {
                return true;
            }
        }
        return false;
    }


    /**
     * 查看方法上是否有权限注解
     *
     * @param method
     * @return
     */
    private boolean isAuthzAnnotationPresent(Method method) {
        for (Class<? extends Annotation> annClass : AUTHZ_ANNOTATION_CLASSES) {
            Annotation a = AnnotationUtils.findAnnotation(method, annClass);
            if (a != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查看方法是否有web注解，是否是一个rest接口
     *
     * @param method
     * @return
     */
    private boolean isWebAnnotationPresent(Method method) {
        for (Class<? extends Annotation> annClass : WEB_ANNOTATION_CLASSES) {
            Annotation a = AnnotationUtils.findAnnotation(method, annClass);
            if (a != null) {
                return true;
            }
        }
        return false;
    }
}