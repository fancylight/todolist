package org.example.todo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName todo_list
 */
@TableName(value ="todo_list")
@Data
public class TodoListEntity implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 待办名称
     */
    private String todoListName;

    /**
     * 组户主键
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 完成状态,0:已完成;1:未完成
     */
    private byte[] status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}