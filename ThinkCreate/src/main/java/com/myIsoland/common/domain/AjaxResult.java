package com.myIsoland.common.domain;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
* @ClassName: AjaxResult
* @Description: TODO(ajax操作消息提醒)
* @author fuce
* @date 2018年8月18日
*
 */
public class AjaxResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 Message 对象
     */
    public AjaxResult()
    {
    }

    /**
     * 返回错误消息
     * 
     * @return 错误消息
     */
    public static Object error()
    {
        return error(1, "操作失败",null);
    }

    /**
     * 返回错误消息
     * 
     * @param msg 内容
     * @return 错误消息
     */
    public static Object error(String msg)
    {
        return error(500, msg,null);
    }

    /**
     * 返回错误消息
     * 
     * @param code 错误码
     * @param msg 内容
     * @return 错误消息
     */
    public static Object error(int code,String msg, Object errMsg)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", code);
        json.put("msg", msg);
        json.put("err", errMsg);
        return JSONObject.toJSON(json);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 内容
     * @return 成功消息
     */
    public static Object success(Object msg)
    {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", 200);
        return JSONObject.toJSON(json);
    }
    
    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static Object success()
    {
        return AjaxResult.success("操作成功");
    }
    
    public static Object successData(int code, Object value){
    	 AjaxResult json = new AjaxResult();
    	 json.put("code", code);
         json.put("data", value);
         return JSONObject.toJSON(json);
    }
   
    
    /**
     * 返回成功消息
     * 
     * @param key 键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public AjaxResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
