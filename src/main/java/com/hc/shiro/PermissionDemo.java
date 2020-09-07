package com.hc.shiro;

import com.hc.utils.ShiroUtil;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by HC on 2017/10/29
 */
public class PermissionDemo {

    public static void main(String[] args) {
        Subject subject = ShiroUtil.login("shiro_permission.ini", "admin", "admin");
        //是否拥有某个权限
        boolean res1 = subject.isPermitted("goods:select");
        System.out.println(res1);

        boolean[] res2 = subject.isPermitted("goods:select", "goods:add", "goods:update", "goods:delete","goods:select/*");
        System.out.println(Arrays.toString(res2));

        boolean res3 = subject.isPermittedAll("goods:select", "goods:delete");
        System.out.println(res3);

        Subject subject2 = ShiroUtil.login("shiro_permission.ini", "zhangsan", "1234");
        subject2.checkPermission("goods:delete");
        subject2.checkPermissions("goods:select2", "goods:delete");
    }

}
