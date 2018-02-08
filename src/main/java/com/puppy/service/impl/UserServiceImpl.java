package com.puppy.service.impl;

import com.puppy.domain.User;
import com.puppy.dto.JwtDTO;
import com.puppy.dto.LoginDTO;
import com.puppy.dto.RegisterDTO;
import com.puppy.enums.ResultEnum;
import com.puppy.repository.IRoleRepository;
import com.puppy.repository.IUserRepository;
import com.puppy.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public ResultEnum login(LoginDTO loginDTO,JwtDTO jwtDTO) {
        Optional<User> user = userRepository.findByName(loginDTO.getUsername());
        if (!user.isPresent())
        {
            return ResultEnum.USER_NOT_EXIST;
        }
        if(!loginDTO.getPassword().equals(user.get().getPassword()))
        {
            return ResultEnum.PASSWORD_ERROR;
        }
        jwtDTO.setPat(user.get().getRoles().stream().map(x -> x.getName()).collect(Collectors.joining(",")));
        return ResultEnum.SUCCESS;
    }

    @Override
    public ResultEnum register(RegisterDTO registerDTO) {
        User user = new User();
        BeanUtils.copyProperties(registerDTO,user);
        user.setName(registerDTO.getUsername());
        user.setRoles(roleRepository.findAll());
        User result = userRepository.save(user);
        if (result.getId()>0){
            return ResultEnum.SUCCESS;
        }
        return ResultEnum.UNKONW_ERROR;
    }
}
