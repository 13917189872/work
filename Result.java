import com.xxx.common.enums.CodeMsg;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import static com.xxx.common.base.HttpStatus.INTERNAL_SERVER_ERROR;
import static com.xxx.common.base.HttpStatus.OK;

@Schema(description = "通用返回对象")
public class Result<T> implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * code:响应编码
     */
    @Schema(description = "响应编码,200:成功", requiredMode = Schema.RequiredMode.REQUIRED)
    protected int code;

    /**
     * code:响应信息
     */
    @Schema(description = "响应信息", requiredMode = Schema.RequiredMode.REQUIRED)
    protected String message;

    /**
     * code:结果
     */
    @Schema(description = "结果", requiredMode = Schema.RequiredMode.REQUIRED)
    protected boolean success = true;

    /**
     * code:响应数据
     */
    @Schema(description = "响应数据")
    protected T data;

    protected Result() {

    }

    protected Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    protected Result(int code, T data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> error() {
        return error(INTERNAL_SERVER_ERROR, "系统繁忙，请稍后再试");
    }

    public static <T> Result<T> error(String message) {
        return error(INTERNAL_SERVER_ERROR, message);
    }

    public static <T> Result<T> error(int code, String message) {
        Result result = new Result(code, message);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> error(int code, T data, String message) {
        Result result = new Result(code, data, message);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> error(CodeMsg msg) {
        return error(msg.getKey(), msg.getMessage());
    }

    public static <T> Result<T> success() {
        return success(null, "处理成功");
    }

    public static <T> Result<T> success(T data) {
        return success(data, "处理成功");
    }

    public static <T> Result<T> success(String message) {
        return success(null, message);
    }


    public static <T> Result<T> success(T data, String message) {
        return new Result<T>(OK, data, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result data(T data) {
        return success(data, (String) null);
    }

}

