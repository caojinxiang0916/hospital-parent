package cn.caojinxiang.hospital.exception;

import cn.caojinxiang.hospital.result.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult error(Exception e) {
        e.printStackTrace();
        return ApiResult.fail();
    }

    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public ApiResult error(YyghException e) {
        e.printStackTrace();
        return ApiResult.fail();
    }
}
