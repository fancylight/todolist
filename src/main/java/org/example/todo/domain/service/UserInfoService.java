package org.example.todo.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.todo.domain.entity.UserInfoEntity;
import org.example.todo.domain.model.UserBO;
import org.example.todo.web.response.UserInfo;

import java.security.NoSuchAlgorithmException;

/**
 *
 */
public interface UserInfoService extends IService<UserInfoEntity> {
    /**
     * 登录,成功后返回token
     *
     * @param userBO
     * @return
     */
    UserInfo login(UserBO userBO) throws NoSuchAlgorithmException;

    /**
     * 登出
     *
     * @param userBO
     * @return
     */
    Boolean loginOut(UserBO userBO);
}
