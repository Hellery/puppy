package com.puppy.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class RoleDTO implements Serializable{

    private Integer id;

    @NotBlank(message = "角色名称不能为空")
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
