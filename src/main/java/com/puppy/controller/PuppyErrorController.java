package com.puppy.controller;

import com.puppy.enums.ResultEnum;
import com.puppy.utils.ResultUtil;
import com.puppy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class PuppyErrorController implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping
    @ResponseBody
    public Result doHandleError(HttpServletRequest request, HttpServletResponse response) {
        //获取是否token验证失败
        Object attribute = request.getAttribute("PUPPY_TOKEN");
        if (!StringUtils.isEmpty(attribute)){
            return ResultUtil.error(ResultEnum.TOKEN_INVALID);
        }
        return ResultUtil.error(ResultEnum.getResultEnumByCode(response.getStatus()));
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
