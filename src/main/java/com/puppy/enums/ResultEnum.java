package com.puppy.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    TOKEN_INVALID(100,"身份校验不通过"),
    PARAM_ERROR(101,"请求参数不正确"),
    USER_NOT_EXIST(102,"用户不存在"),
    PASSWORD_ERROR(103,"密码不正确"),
    METHOD_ERROR(104,"请求Method不支持"),
    ROLE_NOT_EXIST(105,"角色不存在"),
    ROLE_EXIST(106,"角色已存在"),
    IDENTIFY_ERROT(403,"无权访问"),
    NO_FOUND(404,"请求资源不存在"),
    INNER_ERROR(500,"服务器内部错误"),
    ;
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据枚举Code 获取枚举
     * */
    public static ResultEnum getResultEnumByCode(Integer code) {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (resultEnum.getCode().equals(code)) {
                return resultEnum;
            }
        }
        return ResultEnum.UNKONW_ERROR;
    }
}
