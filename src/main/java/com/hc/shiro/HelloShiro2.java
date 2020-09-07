package com.hc.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by HC on 2017/10/29.
 */
public class HelloShiro2 {

    public static void main(String[] args) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        securityManager.setRealm(iniRealm);
        //把SecurityManager实例绑定到SecurityUtils上
        SecurityUtils.setSecurityManager(securityManager);
        //得到当前执行的用户
        Subject subject = SecurityUtils.getSubject();

        //通过当前用户获取Session（Shiro的）
        Session session = subject.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            System.out.println(value);
        }

        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
            try {
                subject.login(token);//身份认证
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }

        //获取当前用户的认证主题
        Object principal = subject.getPrincipal();
        System.out.println(principal);

        subject.logout();//退出，会自动委托给SecurityManager.logout方法退出。
    }

}