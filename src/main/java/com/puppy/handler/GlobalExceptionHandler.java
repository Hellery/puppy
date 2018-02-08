package com.puppy.handler;

import com.puppy.enums.ResultEnum;
import com.puppy.exception.PuppyException;
import com.puppy.utils.ResultUtil;
import com.puppy.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handler(Exception e){
        System.out.println("进入拦截器....");
        if( e instanceof PuppyException){
            PuppyException puppyException = (PuppyException) e;
            return ResultUtil.error(puppyException.getCode(), puppyException.getMessage());
        }
        else if(e instanceof NoHandlerFoundException){
            return ResultUtil.error(ResultEnum.NO_FOUND);
        }
        else if (e instanceof BindException){
            return ResultUtil.error(ResultEnum.PARAM_ERROR);
        }
        else {
            log.info("[系统异常] {}",e);
            return ResultUtil.error(ResultEnum.UNKONW_ERROR);
        }
    }
}
