package org.example.todo.domain.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.todo.common.BeanUtils;
import org.example.todo.common.UserContext;
import org.example.todo.common.exception.BusinessException;
import org.example.todo.domain.entity.TodoListEntity;
import org.example.todo.domain.enums.Status;
import org.example.todo.domain.model.SimplePage;
import org.example.todo.domain.model.TodoListBO;
import org.example.todo.domain.model.UserBO;
import org.example.todo.domain.service.TodoListService;
import org.example.todo.domain.repository.TodoListMapper;
import org.springframework.stereotype.Service;
import java.util.List;

import static org.example.todo.common.exception.ExceptionEnums.NOT_EXIST_DATA;

/**
 *
 */
@Service
public class TodoListServiceImpl extends ServiceImpl<TodoListMapper, TodoListEntity>
        implements TodoListService {

    @Override
    public Boolean addTodoListService(TodoListBO todoListBO) {
        UserBO loginUser = UserContext.get();
        return save(BeanUtils.transform(TodoListEntity.class, todoListBO, (entity, bo) -> {
            entity.setId(System.currentTimeMillis());
            entity.setStatus(Status.UN_COMPLETED);
            entity.setUserId(loginUser.getId());
        }));
    }

    @Override
    public Boolean updateStatus(TodoListBO todoListBO) {
        TodoListEntity byId = getById(todoListBO.getId());
        if (byId == null) {
            throw new BusinessException(NOT_EXIST_DATA);
        }
        Long id = UserContext.get().getId();
        return lambdaUpdate().eq(TodoListEntity::getUserId, id).update(new TodoListEntity().setId(todoListBO.getId()).setStatus(todoListBO.getStatus()));
    }

    @Override
    public Page<TodoListBO> queryPage(SimplePage simplePage) {
        Long id = UserContext.get().getId();
        Page<TodoListEntity> page = page(new Page<>(simplePage.getCurrent(), simplePage.getSize()), new LambdaQueryWrapper<TodoListEntity>().eq(TodoListEntity::getUserId, id));
        List<TodoListBO> todoListBOS = BeanUtils.batchTransform(TodoListBO.class, page.getRecords());
        Page<TodoListBO> objectPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        objectPage.setRecords(todoListBOS);
        return objectPage;
    }

    @Override
    public List<TodoListBO> queryAllByCondition(TodoListBO todoListBO) {
        Long id = UserContext.get().getId();
        List<TodoListEntity> list = lambdaQuery().eq(TodoListEntity::getUserId, id).eq(TodoListEntity::getStatus, todoListBO.getStatus()).list();
        return BeanUtils.batchTransform(TodoListBO.class, list);
    }

}




