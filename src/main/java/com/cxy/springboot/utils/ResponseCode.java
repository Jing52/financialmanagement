package com.cxy.springboot.utils;

/**
 * 0000	调用成功
 * 1001	业务报文格式错误
   1002	参数值必填
   1003	参数值类型错误
   8888 调用失败

   3xxx		订单中心特有业务异常编码
   39xx     订单中心调用外围接口异常编码
   38xx     订单中心调用中心间的异常编码
 */
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS("0000", "操作成功。"),

    /**
     * 错误
     */
    ERROR("9999", "操作时出现异常。"),

    /**
     * 空值
     */
    EMPTY_INPUT("3001", "输入为空值，请输入参数。"),

    /**
     * 格式有误
     */
    ERROR_INPUT("3002", "输入格式有误，请重新输入。"),

    /**
     * 参数值必填
     */
    PARAM_CHECK("1002","参数值必填"),

    /**
     * 未查到相关数据
     */
    QUERY_ERROR("1005","未查到数据");

    private String code;
    private String desc;

    ResponseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
