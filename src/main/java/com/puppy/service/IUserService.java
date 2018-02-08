package com.puppy.service;

import com.puppy.dto.JwtDTO;
import com.puppy.dto.LoginDTO;
import com.puppy.dto.RegisterDTO;
import com.puppy.enums.ResultEnum;

public interface IUserService {

    ResultEnum login(LoginDTO loginDTO,JwtDTO jwtDTO);

    ResultEnum register(RegisterDTO registerDTO);

}


