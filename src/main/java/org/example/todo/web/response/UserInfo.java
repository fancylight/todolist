package org.example.todo.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 22:28
 **/
@Data
@Accessors(chain = true)
@ApiModel
public class UserInfo {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty
    private String token;
}
