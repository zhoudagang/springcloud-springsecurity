package com.everyman.srpingcloud.common;


import com.everyman.srpingcloud.exception.BaseException;
import com.everyman.srpingcloud.exception.ErrorType;
import com.everyman.srpingcloud.exception.SystemErrorType;
import lombok.Data;

import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * @author zhougang
 */
@Data
public class Result<T>
{

    public static final String SUCCESSFUL_CODE = "200";
    public static final String SUCCESSFUL_MSG = "处理成功";

    /**
     * 状态码
     */
    private String code;

    /**
     * 返回内容
     */
    private String msg;

    /**
     * 请求结果生成时间戳
     */
    private Instant time;

    /**
     * 数据对象
     */
    private T data;


    public Result()
    {
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorType errorType)
    {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorType errorType, T data)
    {
        this(errorType);
        this.data = data;
    }

    /**
     * 快速创建成功结果并返回结果数据
     */
    public static Result<Object> success(Object data)
    {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MSG, data);
    }

    /**
     * 内部使用，用于构造成功的结果
     */
    private Result(String code, String msg, T data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * 快速创建成功结果
     */
    public static Result<Object> success()
    {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     */
    public static Result fail()
    {
        return new Result(SystemErrorType.SYSTEM_ERROR);
    }

    /**
     * 系统异常类没有返回数据
     */
    public static Result fail(BaseException baseException)
    {
        return fail(baseException, null);
    }

    /**
     * 系统异常类并返回结果数据
     */
    public static Result fail(BaseException baseException, Object data)
    {
        return new Result<>(baseException.getErrorType(), data);
    }

    /**
     * 系统异常类并返回结果数据
     */
    public static Result fail(ErrorType errorType, Object data)
    {
        return new Result<>(errorType, data);
    }

    /**
     * 系统异常类并返回结果数据
     */
    public static Result fail(ErrorType errorType)
    {
        return Result.fail(errorType, null);
    }

    /**
     * 系统异常类并返回结果数据
     */
    public static Result fail(Object data)
    {
        return new Result<>(SystemErrorType.SYSTEM_ERROR, data);
    }


    /**
     * 成功code=000000
     */
    public boolean isSuccess()
    {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     */
    public boolean isFail()
    {
        return !isSuccess();
    }
}
