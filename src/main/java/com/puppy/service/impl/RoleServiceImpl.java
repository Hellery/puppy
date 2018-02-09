package com.puppy.service.impl;

import com.puppy.domain.Role;
import com.puppy.dto.RoleDTO;
import com.puppy.enums.ResultEnum;
import com.puppy.exception.PuppyException;
import com.puppy.repository.IRoleRepository;
import com.puppy.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private IRoleRepository repository;

    @Override
    public ResultEnum delete(String roleName) {
        Optional<Role> role = repository.findByName(roleName);
        if (!role.isPresent())
        {
            return ResultEnum.ROLE_NOT_EXIST;
        }
        repository.delete(role.get());
        return ResultEnum.SUCCESS;
    }

    @Override
    public ResultEnum save(RoleDTO roleDTO) {
        Optional<Role> role = repository.findByName(roleDTO.getName());
        if (role.isPresent())
        {
            return ResultEnum.ROLE_EXIST;
        }
        Role saveRole = new Role();
        BeanUtils.copyProperties(roleDTO,saveRole);
        Role result = repository.save(saveRole);
        if (result.getId()>0){
            return ResultEnum.SUCCESS;
        }
        return ResultEnum.UNKONW_ERROR;
    }

    @Override
    public Role findRoleByName(String roleName) {
        Optional<Role> role = repository.findByName(roleName);
        if (!role.isPresent())
        {
            throw new PuppyException(ResultEnum.ROLE_NOT_EXIST);
        }
        return role.get();
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> list = repository.findAll();
        if (list.isEmpty()){
            throw new PuppyException(ResultEnum.UNKONW_ERROR);
        }
        return list.stream().map(x-> new RoleDTO(x.getId(),x.getName())).collect(Collectors.toList());
    }
}
