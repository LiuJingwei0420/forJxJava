package com.yzu.jxbookmall.controller;

import com.yzu.jxbookmall.consts.jxbookmallConst;
import com.yzu.jxbookmall.form.UserLoginForm;
import com.yzu.jxbookmall.form.UserRegisterForm;
import com.yzu.jxbookmall.pojo.User;
import com.yzu.jxbookmall.service.IUserService;
import com.yzu.jxbookmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

import static com.yzu.jxbookmall.enums.ResponseEnum.PARAM_ERROR;

/**
 * Created by Will
 *
 * @Create: at 2020-04-30 18:21
 */

@RestController
@Slf4j
public class UserController {


    @Autowired
    private IUserService userService;

    @PostMapping("/user/register")
    public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册提交的参数有误, {} {}",
                    Objects.requireNonNull(bindingResult.getFieldError()).getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(PARAM_ERROR, bindingResult);

        }
        User user = new User();
        BeanUtils.copyProperties(userForm,user);
        return userService.register(user);
    }
    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  HttpSession session) {
        if (bindingResult.hasErrors()) {
            return ResponseVo.error(PARAM_ERROR, bindingResult);
        }

        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());

        //设置session
        session.setAttribute(jxbookmallConst.CURRENT_USER, userResponseVo.getData());

        return userResponseVo;
    }

    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession session) {
        User user = (User)session.getAttribute(jxbookmallConst.CURRENT_USER);

        log.info("/user/ sessionId={}", session.getId());

        return ResponseVo.success(user);
    }
    //TODO 判断登录状态，拦截器
    @PostMapping("/user/logout")
    public ResponseVo logout(HttpSession session) {
        log.info("/user/logout sessionId={}", session.getId());
        session.removeAttribute(jxbookmallConst.CURRENT_USER);
        return ResponseVo.success();
    }

}
