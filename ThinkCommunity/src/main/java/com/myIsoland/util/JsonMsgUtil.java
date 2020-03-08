package com.myIsoland.util;

import com.alibaba.fastjson.JSONObject;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.constant.THINKConstant;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 组报工具类-
 * @author Administrator
 *
 */
public class JsonMsgUtil {

    public static JSONObject successMsg(Object data)
    {
        Map<String,Object> responseMsg = new HashMap<>();

        responseMsg.put(THINKConstant.MSG_HEAD, AjaxResult.success(THINKConstant.MSG_SUCCESS));

        responseMsg.put(THINKConstant.MSG_BODY,data);

        return new JSONObject(responseMsg);
    }
}
