package org.example.todo.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.example.todo.domain.enums.Status;

import javax.validation.constraints.NotNull;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 23:06
 **/
@Data
@ApiModel
public class TodoListQueryRequest {
    @ApiModelProperty("状态")
    @NotNull(message = "状态不能为空")
    private Status status;
}
