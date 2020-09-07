package com.hc.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created by HC on 2017/10/29.
 */
public class ShiroUtil {
    public static Subject login(String confFile, String username, String password) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:" + confFile);
        securityManager.setRealm(iniRealm);
        //把SecurityManager实例绑定到SecurityUtils上
        SecurityUtils.setSecurityManager(securityManager);
        //得到当前执行的用户
        Subject subject = SecurityUtils.getSubject();
        //创建token令牌，用户名/密码
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        if (!subject.isAuthenticated()) {
            try {
                subject.login(token);//身份认证
            } catch (UnknownAccountException e) {
                System.out.println("帐号不存在. There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException e) {
                System.out.println("登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.");
            } catch (ExcessiveAttemptsException e) {
                System.out.println("登录失败次数过多");
            } catch (LockedAccountException e) {
                System.out.println("帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.");
            } catch (DisabledAccountException e) {
                System.out.println("帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.");
            } catch (ExpiredCredentialsException e) {
                System.out.println("帐号已过期. the account for username " + token.getPrincipal() + "  was expired.");
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }
        return subject;
    }
}
