package com.yzu.jxbookmall.service.impl;

import com.yzu.jxbookmall.JxbookmallApplicationTests;
import com.yzu.jxbookmall.enums.ResponseEnum;
import com.yzu.jxbookmall.enums.RoleEnum;
import com.yzu.jxbookmall.pojo.User;
import com.yzu.jxbookmall.service.IUserService;
import com.yzu.jxbookmall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class UserServiceImplTest extends JxbookmallApplicationTests {

    public static final String USERNAME = "will2";

    public static final String PASSWORD = "will2";
    @Autowired
    private IUserService userService;

    @Before
    public void register() {
        User user = new User(USERNAME,PASSWORD,"will2@qq.com", RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }

    @Test
    public void login() {
        ResponseVo<User> responseVo = userService.login(USERNAME, PASSWORD);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }

}