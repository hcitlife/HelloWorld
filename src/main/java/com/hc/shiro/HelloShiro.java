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
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created by HC on 2017/10/29.
 */
public class HelloShiro {

    public static void main(String[] args) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        securityManager.setRealm(iniRealm);
        //把SecurityManager实例绑定到SecurityUtils上
        SecurityUtils.setSecurityManager(securityManager);
        //得到当前执行的用户
        Subject subject = SecurityUtils.getSubject();
        //创建token令牌，用户名/密码
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");

        try {
            subject.login(token);//身份认证
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        //  对于页面的错误消息展示，最好使用如“用户名/密码错误”而不是“用户名错误”/“密码错误”，防止一些恶意用户非法扫描帐号库；
        if (subject.isAuthenticated()) {
            System.out.println("身份认证成功");
        } else {
            System.out.println("身份认证失败");
        }
        subject.logout();//退出，会自动委托给SecurityManager.logout方法退出。
    }

}