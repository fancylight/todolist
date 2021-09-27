package org.example.todo.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.todo.common.BeanUtils;
import org.example.todo.common.annotion.TokenVerify;
import org.example.todo.domain.model.Result;
import org.example.todo.domain.model.SimplePage;
import org.example.todo.domain.model.TodoListBO;
import org.example.todo.domain.service.TodoListService;
import org.example.todo.web.request.TodoListAddRequest;
import org.example.todo.web.request.TodoListQueryRequest;
import org.example.todo.web.request.TodoListUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 22:57
 **/
@RestController
@RequestMapping("todoList")
public class TodoListController {
    @Autowired
    protected TodoListService todoListService;

    @PostMapping("addTodoListService")
    @TokenVerify
    public Result<Boolean> addTodoListService(@Valid @RequestBody TodoListAddRequest todoListAddRequest) {
        return Result.success(todoListService.addTodoListService(BeanUtils.transform(TodoListBO.class, todoListAddRequest)));
    }

    @PostMapping("updateStatus")
    @TokenVerify
    Result<Boolean> updateStatus(@Valid @RequestBody TodoListUpdateRequest todoListAddRequest) {
        return Result.success(todoListService.updateStatus(BeanUtils.transform(TodoListBO.class, todoListAddRequest)));
    }

    @PostMapping("queryPage")
    @TokenVerify
    Result<Page<TodoListBO>> queryPage(@RequestBody SimplePage simplePage) {
        return Result.success(todoListService.queryPage(simplePage));
    }

    @PostMapping("queryAllByCondition")
    @TokenVerify
    Result<List<TodoListBO>> queryAllByCondition(@Valid @RequestBody TodoListQueryRequest todoListQueryRequest) {
        return Result.success(todoListService.queryAllByCondition(BeanUtils.transform(TodoListBO.class, todoListQueryRequest)));
    }
}
