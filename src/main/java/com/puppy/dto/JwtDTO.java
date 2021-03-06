package com.puppy.dto;

import lombok.Data;

@Data
public class JwtDTO {

    /**
     * 用户名
     * */
    private String sub;

    /**
     * 请求的ip地址
     * */
    private String sit;

    /**
     * 用户角色
     * */
    private String pat;

    public JwtDTO() {
    }

    public JwtDTO(String sub, String sit) {
        this.sub = sub;
        this.sit = sit;
    }
}
