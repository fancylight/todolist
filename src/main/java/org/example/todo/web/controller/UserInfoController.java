package org.example.todo.web.controller;

import org.example.todo.common.BeanUtils;
import org.example.todo.common.annotion.NotTokenVerify;
import org.example.todo.domain.model.Result;
import org.example.todo.domain.model.UserBO;
import org.example.todo.domain.service.UserInfoService;
import org.example.todo.web.request.UserLoginRequest;
import org.example.todo.web.response.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 21:22
 **/
@RestController
@RequestMapping("user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @PostMapping("login")
    @NotTokenVerify
    public Result<UserInfo> loginIn(@Validated @RequestBody UserLoginRequest userLoginRequest) throws NoSuchAlgorithmException {
        return Result.success(userInfoService.login(BeanUtils.transform(UserBO.class,userLoginRequest)));
    }

    @GetMapping("loginOut/{id}")
    public Result<Boolean> loginOut(@PathVariable Long id){
        return Result.success(userInfoService.loginOut(new UserBO().setId(id)));
    }
}
