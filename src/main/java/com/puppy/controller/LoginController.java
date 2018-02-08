package com.puppy.controller;

import com.puppy.dto.JwtDTO;
import com.puppy.dto.LoginDTO;
import com.puppy.dto.RegisterDTO;
import com.puppy.enums.ResultEnum;
import com.puppy.exception.PuppyException;
import com.puppy.service.IUserService;
import com.puppy.utils.*;
import com.puppy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/identify")
public class LoginController {

    @Autowired
    private IUserService service;

    @Autowired
    private JwtUtils utils;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@Valid LoginDTO loginDTO, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JwtDTO jwtDTO = new JwtDTO(loginDTO.getUsername(), MD5Util.encode(IpUtil.getIpAddr(req)));
        ResultEnum resultEnum = service.login(loginDTO, jwtDTO);
        if (ResultEnum.SUCCESS.equals(resultEnum)) {
            try {
                //生成Token
                String token = utils.createToken(ObjectMapConvertUtil.objectToMap(jwtDTO));
                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put(utils.getAuthorizationHeaderKey(), token);
                return ResultUtil.success(tokenMap);
            } catch (Exception e) {
                throw new PuppyException(ResultEnum.PASSWORD_ERROR);
            }
        } else {
            return ResultUtil.error(resultEnum);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@Valid RegisterDTO registerDTO, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        registerDTO.setIp(IpUtil.getIpAddr(req));
        ResultEnum resultEnum = service.register(registerDTO);
        if (ResultEnum.SUCCESS.equals(resultEnum)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error(resultEnum);
        }
    }

}
