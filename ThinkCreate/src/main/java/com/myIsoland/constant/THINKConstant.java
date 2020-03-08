package com.myIsoland.constant;

public class THINKConstant {
    /**
     * 报文体
     */
    public static final String MSG_BODY = "body";
    /**
     * 报文头
     */
    public static final String MSG_HEAD = "head";

    public static final Integer SQL_EXCEPTION_CODE = -700;

    public static final Integer SQL_SUCCESS = 1;

    public static final Integer SQL_SUCCESS_CODE = 600;//更新成功代码

    public static final String SQL_SUCCESS_MSG = "更新成功";

    public static final Integer SQL_FAIL_CODE = -600;//更新失败代码

    public static final String SQL_FAIL_MSG = "更新失败";

    public static final Integer SAVE_FILE_FAIL_CODE = -500;//更新失败代码

    public static final String SAVE_FILE_FAIL_MSG = "文件保存失败";


    public static final Integer LOGIN_FAIL_CODE = -100;//登陆失败

    public static final String LOGIN_FAIL_MSG = "登陆失败，密码错误或账户不存在";
}
