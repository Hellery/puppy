package com.puppy.controller;

import com.puppy.domain.Role;
import com.puppy.dto.RoleDTO;
import com.puppy.enums.ResultEnum;
import com.puppy.service.IRoleService;
import com.puppy.utils.ResultUtil;
import com.puppy.vo.Result;
import com.sun.xml.internal.ws.message.RootElementSniffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController
{
    @Autowired
    private IRoleService service;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@Valid RoleDTO roleDTO) throws Exception {
        ResultEnum resultEnum = service.save(roleDTO);
        if (ResultEnum.SUCCESS.equals(resultEnum)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error(resultEnum);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result save(@RequestParam(value = "name") String name) throws Exception {
        ResultEnum resultEnum = service.delete(name);
        if (ResultEnum.SUCCESS.equals(resultEnum)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error(resultEnum);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list() throws Exception {
        return ResultUtil.success(service.findAll());
    }

}
