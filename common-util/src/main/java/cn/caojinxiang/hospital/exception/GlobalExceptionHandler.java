package cn.caojinxiang.hospital.exception;

import cn.caojinxiang.hospital.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public Result error(YyghException e) {
        e.printStackTrace();
        return Result.fail();
    }
}
