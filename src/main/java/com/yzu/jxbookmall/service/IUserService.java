package com.yzu.jxbookmall.service;

import com.yzu.jxbookmall.pojo.User;
import com.yzu.jxbookmall.vo.ResponseVo;

public interface IUserService {

    /**
     * 注册
     */
    ResponseVo<User> register(User user);
    /**
     * 登录
     */
    ResponseVo<User> login(String username, String password);
}
