package com.puppy.service;

import com.puppy.domain.Role;
import com.puppy.dto.RoleDTO;
import com.puppy.enums.ResultEnum;

import java.util.List;

public interface IRoleService {

    ResultEnum save(RoleDTO roleDTO);

    ResultEnum delete(String roleName);

    Role findRoleByName(String roleName);

    List<RoleDTO> findAll();
}
