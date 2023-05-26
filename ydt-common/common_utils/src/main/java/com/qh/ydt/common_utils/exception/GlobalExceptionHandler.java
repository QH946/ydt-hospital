package com.qh.ydt.common_utils.exception;


import com.qh.ydt.common_utils.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理类
 *
 * @author 清欢
 * @date 2023/05/17  09:26:43
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    /**
     * 自定义异常处理方法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HospitalException.class)
    @ResponseBody
    public Result error(HospitalException e) {
        return Result.build(e.getCode(), e.getMessage());
    }
}
