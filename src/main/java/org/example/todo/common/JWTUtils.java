package org.example.todo.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.todo.common.exception.BusinessException;
import org.example.todo.domain.model.UserBO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.example.todo.common.exception.ExceptionEnums.TOKEN_EXPIRED;
import static org.example.todo.common.exception.ExceptionEnums.USER_EXPIRED;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 20:48
 **/
public class JWTUtils {
    public static final String USER_ID = "userId";
    private static String key = "Authorization";
    private static String salt = "Secret";
    private static long expire = 1800L;

    /**
     * 生成token
     * @param userId
     * @return
     */
    public static String generateToken(Long userId) {
        Date expireDate = new Date(System.currentTimeMillis() + expire * 1000);
       return Jwts.builder().setExpiration(expireDate).claim(USER_ID, userId).signWith(SignatureAlgorithm.HS512, salt).compact();
    }

    /**
     * 校验token
     * @param token
     */
    public static UserBO resolveToken(String token) {
        Claims body = Jwts.parser().setSigningKey(salt).parseClaimsJws(token).getBody();
        Long id = Long.valueOf((Integer)body.get(USER_ID));
        if (body.getExpiration().before(new Date())) {
            UserContext.loginOut(id);
            throw new BusinessException(TOKEN_EXPIRED);
        }
        UserBO loginUser = UserContext.getLoginUser(id);
        if (loginUser == null) {
            throw new BusinessException(USER_EXPIRED);
        }
        return loginUser;
    }
}
