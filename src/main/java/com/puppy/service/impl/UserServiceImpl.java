package com.puppy.service.impl;

import com.puppy.domain.User;
import com.puppy.repository.IUserRepository;
import com.puppy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Override
    public String login(String username, String password) {
        Optional<User> user = repository.findByName(username);
        if (!user.isPresent())
        {
            return "用户名不存在！";
        }
        if(!password.equals(user.get().getPassword()))
        {
            return  "密码不正确！";
        }
        return "登录成功！";
    }

    @Override
    public String register(User user) {
        User result = repository.save(user);
        if (result.getId()>0){
            return "注册成功！ "+ result.toString();
        }
        return "注册失败！";
    }
}
