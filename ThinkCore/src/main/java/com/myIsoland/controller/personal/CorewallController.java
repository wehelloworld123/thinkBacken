package com.myIsoland.controller.personal;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.personal.Corewall;
import com.myIsoland.service.personal.CorewallService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户心墙界面")
@RequestMapping("Corewall")
public class CorewallController {
    @Autowired
    private CorewallService corewallService;

    /**
     *@Author:THINKPAD
     *@Description:读取用户心墙信息
     * @param date
     * @param start
     *@Return:java.lang.Object
     *@Data:0:39 2020/1/28
     **/
    @GetMapping("/readCorewall")
    public Object ReadCorewall(String date,int start){
        try {
            return AjaxResult.success(corewallService.GetCorewall(DateUtils.parseDate(date),start));
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }

    @GetMapping("/createCorewall")
    public Object CreateCorewall(String sign,String content,String mood){
        Corewall data = new Corewall();
        try {
            data.setContent(content);
            data.setSign(sign);
            data.setMood(mood);
            int i = corewallService.SaveCorewall(data);
            if(THINKConstant.SQL_SUCCESS.equals(i)){
                return AjaxResult.success(THINKConstant.SQL_SUCCESS_MSG);
            }else{
                return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,THINKConstant.SQL_FAIL_MSG);
            }
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }

}
