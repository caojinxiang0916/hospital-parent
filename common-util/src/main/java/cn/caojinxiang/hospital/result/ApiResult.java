package cn.caojinxiang.hospital.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 全局统一返回结果类
 * @author cjx
 */
@Data
@ApiModel(value = "全局统一返回结果")
@ToString
@Accessors(chain = true)
public class ApiResult<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public ApiResult(){}

    protected static <T> ApiResult<T> build(T data) {
        ApiResult<T> apiResult = new ApiResult<T>();
        if (data != null)
            apiResult.setData(data);
        return apiResult;
    }

    public static <T> ApiResult<T> build(T body, ResultCodeEnum resultCodeEnum) {
        ApiResult<T> apiResult = build(body);
        apiResult.setCode(resultCodeEnum.getCode());
        apiResult.setMessage(resultCodeEnum.getMessage());
        return apiResult;
    }

    public static <T> ApiResult<T> build(Integer code, String message) {
        ApiResult<T> apiResult = build(null);
        apiResult.setCode(code);
        apiResult.setMessage(message);
        return apiResult;
    }

    public static<T> ApiResult<T> ok(){
        return ApiResult.ok(null);
    }

    /**
     * 操作成功
     * @param data
     * @param <T>
     * @return
     */
    public static<T> ApiResult<T> ok(T data){
        ApiResult<T> apiResult = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static<T> ApiResult<T> fail(){
        return ApiResult.fail(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> ApiResult<T> fail(T data){
        ApiResult<T> apiResult = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public ApiResult<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public ApiResult<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    public boolean isOk() {
        if(this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }
}
