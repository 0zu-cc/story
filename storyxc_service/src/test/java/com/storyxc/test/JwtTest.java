package com.storyxc.test;

import com.storyxc.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/23 10:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    @Test
    public void testCreateJwt(){
        String story = JwtUtil.createJWT(UUID.randomUUID().toString(), "story", new Date().getTime());
        System.out.println(story);
    }

    @Test
    public void parseJwt() throws Exception {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1NjQ1YTY4Mi01YTQwLTQ5NDYtOTFhNC1mOTlmNDBjOGE0NDMiLCJzdWIiOiJvcmcuc3ByaW5nZnJhbWV3b3JrLnNlY3VyaXR5LmNvcmUudXNlcmRldGFpbHMuVXNlckA1ODYwMzRmOiBVc2VybmFtZTogYWRtaW47IFBhc3N3b3JkOiBbUFJPVEVDVEVEXTsgRW5hYmxlZDogdHJ1ZTsgQWNjb3VudE5vbkV4cGlyZWQ6IHRydWU7IGNyZWRlbnRpYWxzTm9uRXhwaXJlZDogdHJ1ZTsgQWNjb3VudE5vbkxvY2tlZDogdHJ1ZTsgTm90IGdyYW50ZWQgYW55IGF1dGhvcml0aWVzIiwiaXNzIjoiYWRtaW4iLCJpYXQiOjE1NzcwOTY5MzAsImV4cCI6MTU3NzEwMDUzMH0.Ve0MVSGvzov_MCFfbuPUyg3fgDtfnR5r0INsnAXnLSQ";
        Claims claims = JwtUtil.parseJWT(jwt);
        System.out.println(claims);
        System.out.println(claims.getSubject());
    }
}
