package org.example.todo.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 21:12
 **/
public class AuthInterceptor implements HandlerInterceptor {
    private String token = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //白名单跳过
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            NotTokenVerifery annotation = handlerMethod.getMethod().getAnnotation(NotTokenVerifery.class);
//            if (annotation != null) {
//                return true;
//            }
//
//        } else {
//            return true;
//        }
//        //token校验
//        String token = request.getHeader(this.token);
//        if (StringUtils.isEmpty(token)) {
//            throw new BusinessException(USER_NOT_LOGIN);
//        }
//        UserBO userBO = JWTUtils.resolveToken(token);
//        UserContext.set(userBO);
        return true;
    }
}
