package org.example.todo.common.exception;

import org.example.todo.domain.model.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 22:01
 **/
@ControllerAdvice
@ResponseBody
public class CommonExceptionDeal {
    @ExceptionHandler
    public Result exceptionDeal(Exception e){
        if (e instanceof BusinessException) {
            return Result.error(500,((BusinessException) e).getMes());
        }
        e.printStackTrace();
        return Result.error(500,e.getMessage());
    }
}
