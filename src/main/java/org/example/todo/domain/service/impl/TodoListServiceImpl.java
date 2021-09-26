package org.example.todo.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.todo.domain.entity.TodoListEntity;
import org.example.todo.domain.service.TodoListService;
import org.example.todo.domain.repository.TodoListMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TodoListServiceImpl extends ServiceImpl<TodoListMapper, TodoListEntity>
    implements TodoListService{

}




