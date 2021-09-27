package org.example.todo.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.util.security.MD5Encoder;
import org.example.todo.common.BeanUtils;
import org.example.todo.common.JWTUtils;
import org.example.todo.common.UserContext;
import org.example.todo.common.exception.BusinessException;
import org.example.todo.common.exception.ExceptionEnums;
import org.example.todo.domain.entity.UserInfoEntity;
import org.example.todo.domain.model.UserBO;
import org.example.todo.domain.service.UserInfoService;
import org.example.todo.domain.repository.UserInfoMapper;
import org.example.todo.web.response.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.example.todo.common.exception.ExceptionEnums.PASSWORD_ERROR;

/**
 *
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity>
        implements UserInfoService {
    @Override
    public UserInfo login(UserBO userBO) {
        Optional<UserInfoEntity> userInfoEntity = lambdaQuery().eq(UserInfoEntity::getUserName, userBO.getUserName()).oneOpt();
        if (userInfoEntity.isEmpty()) {
            throw new BusinessException(ExceptionEnums.NOT_EXIST_DATA);
        }

        String md5Password = Base64.getEncoder().encodeToString(userBO.getPassword().getBytes(StandardCharsets.UTF_8));
        if (!userInfoEntity.get().getPassword().equals(md5Password)) {
            throw new BusinessException(PASSWORD_ERROR);
        }
        UserContext.loginUser(userInfoEntity.get().getId(),BeanUtils.transform(UserBO.class,userInfoEntity.get()));
        return BeanUtils.transform(UserInfo.class,userInfoEntity.get()).setToken(JWTUtils.generateToken(userInfoEntity.get().getId()));
    }

    @Override
    public Boolean loginOut(UserBO userBO) {
        return UserContext.loginOut(userBO.getId());
    }
}




