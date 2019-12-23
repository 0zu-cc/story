package com.storyxc.config;

import com.storyxc.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/23 9:26
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error("服务器忙,请稍后重试",e);
        return new Result(false,"服务器忙,请稍后重试");
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result error(AccessDeniedException e){
        log.error("权限不足",e);
        return new Result(false,"权限不足");
    }
}
