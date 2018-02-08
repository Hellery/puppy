package com.puppy.exception;

import com.puppy.enums.ResultEnum;
import lombok.Data;

@Data
public class PuppyException extends RuntimeException {
    private Integer code;

    public PuppyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
