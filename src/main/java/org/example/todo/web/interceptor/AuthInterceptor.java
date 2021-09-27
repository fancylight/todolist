package org.example.todo.web.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.example.todo.common.JWTUtils;
import org.example.todo.common.UserContext;
import org.example.todo.common.annotion.TokenVerify;
import org.example.todo.common.exception.BusinessException;
import org.example.todo.domain.model.UserBO;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.example.todo.common.exception.ExceptionEnums.USER_NOT_LOGIN;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 21:12
 **/
public class AuthInterceptor implements HandlerInterceptor {
    private String token = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //白名单跳过
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            TokenVerify annotation = handlerMethod.getMethod().getAnnotation(TokenVerify.class);
            if (annotation != null) {
                //token校验
                String token = request.getHeader(this.token);
                if (StringUtils.isEmpty(token)) {
                    throw new BusinessException(USER_NOT_LOGIN);
                }
                UserBO userBO = JWTUtils.resolveToken(token);
                UserContext.set(userBO);
                return true;
            }
        } else {
            return true;
        }
        return true;
    }
}
