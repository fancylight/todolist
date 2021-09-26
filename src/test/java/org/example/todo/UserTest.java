package org.example.todo;

import org.example.todo.web.request.UserLoginRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 17:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ToDoListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserTest {
    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:8080/user";
    @Test
    public void login() {
        UserLoginRequest loginRequest = new UserLoginRequest().setUserName("test").setPassword("123456");
        ResponseEntity<String> post = restTemplate.postForEntity(url + "/login", loginRequest, String.class);
        Assertions.assertNotNull(post.getBody());
    }
}
