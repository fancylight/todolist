package org.example.todo;


import org.example.todo.domain.enums.Status;
import org.example.todo.domain.model.Result;
import org.example.todo.domain.model.SimplePage;
import org.example.todo.web.request.TodoListAddRequest;
import org.example.todo.web.request.TodoListQueryRequest;
import org.example.todo.web.request.TodoListUpdateRequest;
import org.example.todo.web.request.UserLoginRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 17:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ToDoListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SimpleTest {
    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:8080/user";
    private String todoUrl = "http://localhost:8080/todoList";
    private String token;
    MultiValueMap multiValueMap = new LinkedMultiValueMap();

    @Before
    public void login() {
        UserLoginRequest loginRequest = new UserLoginRequest().setUserName("test").setPassword("123456");
        ResponseEntity<Result> post = restTemplate.postForEntity(url + "/login", loginRequest, Result.class);
        Assertions.assertNotNull(post.getBody());
        Map data = (Map) post.getBody().getData();
        token = (String) data.get("token");
        multiValueMap.put("Authorization", Collections.singletonList(token));
        Assertions.assertNotNull(token);
    }

    @Test
    public void loginOut() {
        Result logOut = restTemplate.getForObject(url + "/loginOut/111", Result.class);
        Assertions.assertNotNull(logOut.getData());
    }

    @Test
    public void addToDoList() {
        TodoListAddRequest todoListAddRequest = new TodoListAddRequest();
        todoListAddRequest.setTodoListName("测试数据");
        HttpEntity<TodoListAddRequest> todoListAddRequestHttpEntity = new HttpEntity(todoListAddRequest, multiValueMap);
        ResponseEntity<Result> exchange = restTemplate.exchange(todoUrl + "/addTodoListService", HttpMethod.POST, todoListAddRequestHttpEntity, Result.class);
       Assertions.assertTrue((Boolean) exchange.getBody().getData());
    }
    private Long id;
    @Test
    public void queryAllByCondition() {
        TodoListQueryRequest todoListQueryRequest = new TodoListQueryRequest();
        todoListQueryRequest.setStatus(Status.UN_COMPLETED);
        HttpEntity<TodoListAddRequest> todoListAddRequestHttpEntity = new HttpEntity(todoListQueryRequest, multiValueMap);
        ResponseEntity<Result> exchange = restTemplate.exchange(todoUrl + "/queryAllByCondition", HttpMethod.POST, todoListAddRequestHttpEntity, Result.class);
        List<Map> data = (List<Map>) exchange.getBody().getData();
        Assertions.assertEquals(data.get(0).get("todoListName"),"test用户数据");
        id = Long.valueOf(data.get(0).get("id")+"");
    }

    @Test
    public void queryPage() {
        SimplePage simplePage = new SimplePage();
        simplePage.setSize(5);
        simplePage.setCurrent(1);
        HttpEntity<TodoListAddRequest> todoListAddRequestHttpEntity = new HttpEntity(simplePage, multiValueMap);
        ResponseEntity<Result> exchange = restTemplate.exchange(todoUrl + "/queryPage", HttpMethod.POST, todoListAddRequestHttpEntity, Result.class);
        Map data = (Map) exchange.getBody().getData();
        Assertions.assertEquals(data.get("size"),5);
    }

    @Test
    public void updateStatus() {
        queryAllByCondition();
        TodoListUpdateRequest todoListUpdateRequest = new TodoListUpdateRequest();
        todoListUpdateRequest.setId(id);
        todoListUpdateRequest.setStatus(Status.COMPLETED);
        HttpEntity<TodoListAddRequest> todoListAddRequestHttpEntity = new HttpEntity(todoListUpdateRequest, multiValueMap);
        ResponseEntity<Result> exchange = restTemplate.exchange(todoUrl + "/updateStatus", HttpMethod.POST, todoListAddRequestHttpEntity, Result.class);
        Assertions.assertTrue((Boolean) exchange.getBody().getData());
    }
}
