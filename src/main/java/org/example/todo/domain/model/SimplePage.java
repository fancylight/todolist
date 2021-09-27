package org.example.todo.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 22:46
 **/
@Data
@ApiModel
public class SimplePage {

    /**
     * 总数
     */
    @ApiModelProperty("总数")
    protected long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty("每页显示条数")
    protected long size = 10;

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    protected long current = 1;
}
