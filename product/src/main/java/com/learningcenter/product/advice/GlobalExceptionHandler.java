package com.learningcenter.product.advice;

import com.learningcenter.product.vo.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 *
 * @author Jason
 * @email 285290078@qq.com
 * @create 2018-06-13 20:08
 **/
@ControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response handle(HttpServletRequest request, Exception e){
        e.printStackTrace();
        return Response.error(500,e.getMessage());
    }
}
