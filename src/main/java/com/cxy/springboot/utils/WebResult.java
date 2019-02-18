package com.cxy.springboot.utils;

import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @Auther: cxy
 * @Date: 2019/1/8
 * @Description: 登陆
 */
public class WebResult extends HashMap<String, Object>
{
    /**
     * 成功响应编码
     */
    public static final String SUCCESS_CODE = "0000";

    /**
     * 其它错误编码
     */
    public static final String OTHER_ERROR_CODE = "8888";

    /**
     * 响应码Key
     */
    public static final String RSP_CODE_KEY = "CODE";

    /**
     * 响应描述Key
     */
    public static final String RSP_DESC_KEY = "DESC";

    /**
     * 响应描述data
     */
    public static final String RSP_DATA_KEY = "DATA";

    /**
     * 分页查询时的数量
     */
    public static final String RSP_COUNT = "count";

    /**
     * 分页查询时的数据
     */
    public static final String RSP_DATA = "data";


    /**
     * 构造函数
     */
    public WebResult()
    {
        super();
    }

    /**
     * 获取错误编码
     *
     * @return 错误编码
     */
    public String getErrorCode()
    {
        return super.get(RSP_CODE_KEY).toString();
    }

    /**
     * 获取错误描述
     *
     * @return 错误描述
     */
    public String getMessage()
    {
        return super.get(RSP_DESC_KEY).toString();
    }

    /**
     * 设置错误编码
     *
     * @param error 错误编码
     */
    public void setError(String error)
    {
        super.put(RSP_CODE_KEY, error);
    }

    /**
     * 设置错误描述
     *
     * @param message 错误描述
     */
    public void setMessage(String message)
    {
        super.put(RSP_DESC_KEY, message);
    }

    /**
     * 成功响应
     *
     * @return WebResult
     */
    public WebResult ok()
    {
        super.put(RSP_CODE_KEY, SUCCESS_CODE);
        return this;
    }

    /**
     * 成功响应
     *
     * @param message 成功信息
     * @return WebResult
     */
    public WebResult ok(String message)
    {
        super.put(RSP_CODE_KEY, SUCCESS_CODE);
        super.put(RSP_DESC_KEY, message);
        return this;
    }

    /**
     * 成功响应
     *
     * @param message 成功信息
     * @param message data
     * @return WebResult
     */
    public WebResult ok(String message,Object data)
    {
        super.put(RSP_CODE_KEY, SUCCESS_CODE);
        super.put(RSP_DESC_KEY, message);
        super.put(RSP_DATA_KEY, data);
        return this;
    }

    /**
     * * 失败响应
     *
     * @param errorCode 错误响应编码
     * @param errorMsg  错误响应信息
     * @param  data
     * @return WebResult
     */
    public WebResult fail(String errorCode, String errorMsg,Object data)
    {
        if (StringUtils.isEmpty(errorCode))
        {
            super.put(RSP_CODE_KEY, OTHER_ERROR_CODE);
            super.put(RSP_DESC_KEY, errorMsg);
            super.put(RSP_DATA_KEY, data);
        }
        else
        {
            super.put(RSP_CODE_KEY, errorCode);
            super.put(RSP_DESC_KEY, errorMsg);
            super.put(RSP_DATA_KEY, data);
        }
        return this;
    }

    /**
     * * 失败响应
     *
     * @param errorCode 错误响应编码
     * @param errorMsg  错误响应信息
     * @return WebResult
     */
    public WebResult fail(String errorCode, String errorMsg)
    {
        if (StringUtils.isEmpty(errorCode))
        {
            super.put(RSP_CODE_KEY, OTHER_ERROR_CODE);
            super.put(RSP_DESC_KEY, errorMsg);
        }
        else
        {
            super.put(RSP_CODE_KEY, errorCode);
            super.put(RSP_DESC_KEY, errorMsg);
        }
        return this;
    }

    /**
     * 失败响应
     *
     * @param message 失败信息
     * @return WebResult
     */
    public WebResult fail(String message,Object data)
    {
        super.put(RSP_CODE_KEY, OTHER_ERROR_CODE);
        super.put(RSP_DESC_KEY, (null == message || "".equals(message)) ? "unknown " +
                "error" : message);
        super.put(RSP_DATA_KEY, data);
        return this;
    }

    /**
     * 失败响应
     *
     * @param message 失败信息
     * @return WebResult
     */
    public WebResult fail(String message)
    {
        super.put(RSP_CODE_KEY, OTHER_ERROR_CODE);
        super.put(RSP_DESC_KEY, (null == message || "".equals(message)) ? "unknown " +
                "error" : message);
        return this;
    }

    /**
     * 设置 key-value
     *
     * @param key   key
     * @param value value
     * @return WebResult
     */
    public WebResult set(String key, Object value)
    {
        super.put(key, value);
        return this;
    }

    /**
     * 设置数量
     * @param value
     * @return
     */
    public WebResult setCount(Object value)
    {
        super.put(RSP_COUNT, value);
        return this;
    }

    /**
     * 设置data
     * @param value
     * @return
     */
    public WebResult setData(Object value)
    {
        super.put(RSP_DATA, value);
        return this;
    }
}
