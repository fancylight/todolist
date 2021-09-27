package org.example.todo.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.example.todo.domain.enums.Status;

import java.util.Date;


/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 22:36
 **/
@Data
@ApiModel
@Accessors(chain = true)
public class TodoListBO {
    @ApiModelProperty("id")
    private Long  id;
    /**
     * 待办名称
     */
    @ApiModelProperty("待办名称")
    private String todoListName;

    /**
     * 组户主键
     */
    @ApiModelProperty("组户主键")
    private Long userId;
    /**
     * 完成状态,0:已完成;1:未完成
     */
    @ApiModelProperty("完成状态,0:已完成;1:未完成")
    private Status status;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
