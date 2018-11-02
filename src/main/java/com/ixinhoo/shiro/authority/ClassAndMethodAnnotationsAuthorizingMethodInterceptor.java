package com.ixinhoo.shiro.authority;

import com.ixinhoo.api.shiro.annotation.ProhibitedAnnotationMethodInterceptor;
import com.google.common.collect.Lists;
import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.authz.aop.*;
import org.apache.shiro.spring.aop.SpringAnnotationResolver;
import org.apache.shiro.spring.security.interceptor.AopAllianceAnnotationsAuthorizingMethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册注解拦截器
 * @author 448778074@qq.com (cici)
 */
public class ClassAndMethodAnnotationsAuthorizingMethodInterceptor extends AopAllianceAnnotationsAuthorizingMethodInterceptor {


    public ClassAndMethodAnnotationsAuthorizingMethodInterceptor() {
        List<AuthorizingAnnotationMethodInterceptor> interceptors = Lists.newArrayList();
//        List<AuthorizingAnnotationMethodInterceptor> interceptors =
//                new ArrayList<AuthorizingAnnotationMethodInterceptor>(5);

        //use a Spring-specific Annotation resolver - Spring's AnnotationUtils is nicer than the
        //raw JDK resolution process.
        AnnotationResolver resolver = new SpringAnnotationResolver();
        //we can re-use the same resolver instance - it does not retain state:
        interceptors.add(new RoleAnnotationMethodInterceptor(resolver));
        interceptors.add(new PermissionAnnotationMethodInterceptor(resolver));
        interceptors.add(new AuthenticatedAnnotationMethodInterceptor(resolver));
        interceptors.add(new UserAnnotationMethodInterceptor(resolver));
        interceptors.add(new GuestAnnotationMethodInterceptor(resolver));
        //自定义的shiro-spring权限类注解可以加入到这里 cici
        interceptors.add(new ProhibitedAnnotationMethodInterceptor(resolver));
        setMethodInterceptors(interceptors);
    }



}