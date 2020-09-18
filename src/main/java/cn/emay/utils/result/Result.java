package cn.emay.utils.result;

/**
 * 统一返回结果
 *
 * @author Frank
 */
public class Result extends SuperResult<Object> {

    private static final long serialVersionUID = 1L;

    /**
     * @param success 是否成功
     * @param code    结果吗
     * @param message 结果信息
     * @param result  结果对象
     */
    public Result(Boolean success, String code, String message, Object result) {
        super(success, code, message, result);
    }

    /**
     * 错误的结果
     *
     * @param code    结果吗
     * @param message 结果信息
     * @param result  结果对象
     */
    public static Result badResult(String code, String message, Object result) {
        return new Result(false, code, message, result);
    }

    /**
     * 错误的结果
     *
     * @param code    结果吗
     * @param message 结果信息
     * @param result  结果对象
     */
    public static Result badResult(int code, String message, Object result) {
        return new Result(false, String.valueOf(code), message, result);
    }

    /**
     * 错误的结果
     *
     * @param message 结果信息
     * @param result  结果对象
     */
    public static Result badResult(String message, Object result) {
        return new Result(false, "-1", message, result);
    }

    /**
     * 错误的结果
     *
     * @param badMessage 结果信息
     */
    public static Result badResult(String badMessage) {
        return new Result(false, "-1", badMessage, null);
    }

    /**
     * 错误的结果
     */
    public static Result badResult() {
        return new Result(false, "-1", "fail result", null);
    }

    /**
     * 正确的结果
     *
     * @param code    结果吗
     * @param message 结果信息
     * @param result  结果对象
     */
    public static Result rightResult(int code, String message, Object result) {
        return new Result(true, String.valueOf(code), message, result);
    }

    /**
     * 正确的结果
     *
     * @param code    结果吗
     * @param message 结果信息
     * @param result  结果对象
     */
    public static Result rightResult(String code, String message, Object result) {
        return new Result(true, code, message, result);
    }

    /**
     * 正确的结果
     *
     * @param message 结果信息
     * @param result  结果对象
     */
    public static Result rightResult(String message, Object result) {
        return new Result(true, "0", message, result);
    }

    /**
     * 正确的结果
     *
     * @param result 结果对象
     */
    public static Result rightResult(Object result) {
        return new Result(true, "0", "success", result);
    }

    /**
     * 正确的结果
     */
    public static Result rightResult() {
        return new Result(true, "0", "success", null);
    }
}
