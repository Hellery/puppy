package com.puppy.controller;

import com.puppy.dto.JwtDTO;
import com.puppy.dto.LoginDTO;
import com.puppy.enums.ResultEnum;
import com.puppy.exception.PuppyException;
import com.puppy.repository.IUserRepository;
import com.puppy.utils.IpUtil;
import com.puppy.utils.JwtUtils;
import com.puppy.utils.MD5Util;
import com.puppy.utils.ObjectMapConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/identify")
public class LoginController {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtUtils utils;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@Valid LoginDTO loginDTO, HttpServletRequest req, HttpServletResponse resp) throws Exception{
        JwtDTO jwtDTO = new JwtDTO(loginDTO.getUsername(),MD5Util.encode(IpUtil.getIpAddr(req)));
        if (Objects.nonNull(loginDTO)){
            userRepository.findByName(loginDTO.getUsername())
                    .orElseThrow(()->new PuppyException(ResultEnum.USER_NOT_EXIST));
        }
        try {
            //生成Token
            String token = utils.createToken(ObjectMapConvertUtil.objectToMap(jwtDTO));
            //将Token写入到Http头部
            resp.addHeader("Authorization",token);
            return token;
        }catch (Exception authentication){
            throw new PuppyException(ResultEnum.PASSWORD_ERROR);
        }
    }
}
