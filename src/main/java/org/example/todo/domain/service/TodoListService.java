package org.example.todo.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.todo.domain.entity.TodoListEntity;
import org.example.todo.domain.model.SimplePage;
import org.example.todo.domain.model.TodoListBO;

import java.util.List;

/**
 *
 */
public interface TodoListService extends IService<TodoListEntity> {
    Boolean addTodoListService(TodoListBO todoListBO);
    Boolean updateStatus(TodoListBO todoListBO);
    Page<TodoListBO> queryPage(SimplePage simplePage);
    List<TodoListBO> queryAllByCondition(TodoListBO todoListBO);
}
