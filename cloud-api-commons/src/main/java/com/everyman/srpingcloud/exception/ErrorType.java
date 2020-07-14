package com.everyman.srpingcloud.exception;

/**
 * @author everyman
 */
public interface ErrorType
{
    /**
     * 返回code
     *
     * @return String
     */
    String getCode();

    /**
     * 返回msg
     *
     * @return String
     */
    String getMsg();
}
