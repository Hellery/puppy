package com.puppy.service;

import com.puppy.domain.User;

public interface IUserService {

    String login(String username,String password);

    String register(User user);
}


