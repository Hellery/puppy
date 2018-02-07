package com.puppy.repository;

import com.puppy.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserRepositoryTest {

    @Autowired
    private IUserRepository repository;

    @Test
    public void save(){
        User user = new User();
        user.setName("zlzhou");
        user.setPassword("123");
        User result = repository.save(user);
        System.out.println(result.toString());
    }

    @Test
    public void findByUsername() {
        Optional<User> user = repository.findByName("zlzhou");
        if (user.isPresent()) {
            System.out.println(user.toString());
        }
        else {
            System.out.println("用户不存在！");
        }
    }
}