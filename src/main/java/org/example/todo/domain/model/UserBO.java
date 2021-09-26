package org.example.todo.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 17:37
 **/
@ApiModel
@Data
@Accessors(chain = true)
public class UserBO {
    @ApiModelProperty("用户编号")
    private Long id;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户密码")
    private String password;
}
