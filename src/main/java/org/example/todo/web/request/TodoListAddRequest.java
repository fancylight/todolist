package org.example.todo.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 22:58
 **/
@Data
@ApiModel
public class TodoListAddRequest {
    @ApiModelProperty("待办名称")
    @NotNull(message = "待办名称不能为空")
    private String todoListName;
}
