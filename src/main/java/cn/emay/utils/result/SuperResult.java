package cn.emay.utils.result;

import java.io.Serializable;

/**
 * 统一返回结果<自定义结果类型>
 *
 * @param <T>
 * @author Frank
 */
public class SuperResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean success;

    private String code;

    private String message;

    private T result;

    /**
     * @param success 是否成功
     * @param code    结果吗
     * @param message 结果信息
     * @param result  结果对象
     */
    public SuperResult(Boolean success, String code, String message, T result) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    /**
     * 错误的结果
     *
     * @param code    结果吗
     * @param message 结果信息
     * @param result  结果对象
     */
    public static <T> SuperResult<T> badResult(String code, String message, T result) {
        return new SuperResult<>(false, code, message, result);
    }

    /**
     * 错误的结果
     *
     * @param code    结果吗
     * @param message 结果信息
     * @param result  结果对象
     */
    public static <T> SuperResult<T> badResult(int code, String message, T result) {
        return new SuperResult<>(false, String.valueOf(code), message, result);
    }

    /**
     * 错误的结果
     *
     * @param message 结果信息
     * @param result  结果对象
     */
    public static <T> SuperResult<T> badResult(String message, T result) {
        return new SuperResult<>(false, "-1", message, result);
    }

    /**
     * 错误的结果
     *
     * @param badMessage 结果信息
     */
    public static <T> SuperResult<T> badResult(String badMessage) {
        return new SuperResult<>(false, "-1", badMessage, null);
    }

    /**
     * 错误的结果
     */
    public static <T> SuperResult<T> badResult() {
        return new SuperResult<>(false, "-1", "fail result", null);
    }

    /**
     * 正确的结果
     *
     * @param code    结果吗
     * @param message 结果信息
     * @param result  结果对象
     */
    public static <T> SuperResult<T> rightResult(int code, String message, T result) {
        return new SuperResult<>(true, String.valueOf(code), message, result);
    }

    /**
     * 正确的结果
     *
     * @param code    结果吗
     * @param message 结果信息
     * @param result  结果对象
     */
    public static <T> SuperResult<T> rightResult(String code, String message, T result) {
        return new SuperResult<>(true, code, message, result);
    }

    /**
     * 正确的结果
     *
     * @param message 结果信息
     * @param result  结果对象
     */
    public static <T> SuperResult<T> rightResult(String message, T result) {
        return new SuperResult<>(true, "0", message, result);
    }

    /**
     * 正确的结果
     *
     * @param result 结果对象
     */
    public static <T> SuperResult<T> rightResult(T result) {
        return new SuperResult<>(true, "0", "success", result);
    }

    /**
     * 正确的结果
     */
    public static <T> SuperResult<T> rightResult() {
        return new SuperResult<>(true, "0", "success", null);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
