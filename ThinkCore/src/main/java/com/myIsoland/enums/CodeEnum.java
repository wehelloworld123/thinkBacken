package com.myIsoland.enums;

/**
 * 应答码枚举类
 *
 * @create 2018-8-21
 */
public enum CodeEnum {
    /**
     * 未登陆
     */
    USER_NOT_LOGIN(100, "未登陆"),
    /**
     * 未注册
     */
    USER_NOT_REGISTER(101, "用户未注册"),

    LOGIN_FAIL_CODE(-100,"登陆失败，密码错误或账户不存在"),

    SQL_EXCEPTION_CODE (-700,"数据库更新失败"),

    REDIS_EXCEPTION (-701,"系统缓存异常"),

    INCORRECT_REQ_PARAM(-120,"错误的请求参数"),

    UNKOWN_ERROR(000,"未知错误"),

    SAVE_FILE_FAIL_CODE(-500,"文件保存失败"),

    ILLEGAL_STRING(200,"非法字符"),

    SQL_SUCCESS(700,"数据库更新成功");


    /**
     * 响应代码
     */
    private final int code;

    /**
     * 响应消息
     */
    private final String message;

    CodeEnum(int _code, String _message) {

        this.code = _code;
        this.message = _message;

    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 通过枚举code获取对应的message
     *
     * @return 取不到时返回null
     */
    public static String getMessageByCode(int code) {
        for (CodeEnum _enum : values()) {
            if (_enum.getCode() == code) {
                return _enum.getMessage();
            }
        }
        return null;
    }

    /**
     * 通过枚举code获取枚举对象
     *
     * @return 取不到时返回null
     */
    public static CodeEnum getByCode(int code) {
        for (CodeEnum _enum : values()) {
            if (_enum.getCode() == code) {
                return _enum;
            }
        }
        return null;
    }

}
