package com.hc.shiro;

import com.hc.utils.ShiroUtil;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by HC on 2017/10/29.
 */
public class RoleDemo {

    public static void main(String[] args) {
        Subject subject = ShiroUtil.login("shiro_role.ini", "admin", "admin");
        //是否拥有某个角色
        boolean res1 = subject.hasRole("role1");
        System.out.println(res1);
        boolean res2 = subject.hasRole("role6");
        System.out.println(res2);

        boolean[] res3 = subject.hasRoles(Arrays.asList("role1", "role2", "role6", "role3"));
        System.out.println(Arrays.toString(res3));

        boolean res4 = subject.hasAllRoles(Arrays.asList("role4", "role2", "role3"));
        System.out.println(res4);

        boolean res5 = subject.hasAllRoles(Arrays.asList("role4", "role2", "role8"));
        System.out.println(res5);

        //相对于has来说check没有返回值，在没有验证通过时会报错
        subject.checkRole("role1");
        subject.checkRole("role5");
    }

}
