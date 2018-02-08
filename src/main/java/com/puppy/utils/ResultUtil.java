package com.puppy.utils;

import com.puppy.enums.ResultEnum;
import com.puppy.vo.Result;

public class ResultUtil {

    public static Result success(){
        return success(null);
    }

    public static Result success(Object object){
        Result result = new Result(0,"成功");
        result.setData(object);
        return result;
    }

    public static Result error(Integer code, String msg){
        Result result = new Result(code,msg);
        return result;
    }

    public static Result error(ResultEnum resultEnum){
        Result result = new Result(resultEnum.getCode(),resultEnum.getMsg());
        return result;
    }

}
