package org.example.todo.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 21:23
 **/
@ApiModel
@Data
@Accessors(chain = true)
public class UserLoginRequest {
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户密码")
    private String password;
}
