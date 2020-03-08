package com.myIsoland.controller.think;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.component.IntegerComponent;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.service.think.QuotationService;
import com.myIsoland.service.think.TheoryService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户语录查看")
@RequestMapping("UserQuota")
public class UserQuotaController {
    @Autowired
    private TheoryService theoryService;

    @Autowired
    private QuotationService quotationService;

    @GetMapping("/readUserTheory")
    public Object ReadUserTheoryTitle(String key){
        //String userId = IntegerComponent.generateUserKey(key);
            //return AjaxResult.success(theoryService.GetTheoryByUserId(userId,1));
        return AjaxResult.success(theoryService.GetTheoryByUserId(key,0));
    }

    @GetMapping("/readUserQuotations")
    public Object ReadUserQuotations(String key,String theoryNo,int page){
        //String userId = IntegerComponent.generateUserKey(key);

            //return AjaxResult.success(quotationService.GetQuotationByUserId(userId,theoryNo,page));
        return AjaxResult.success(quotationService.GetQuotationByUserId(key,theoryNo,page));

    }


    @GetMapping("/readUserQuotation")
    public Object ReadUserQuotation(int id){
        //String userId = IntegerComponent.generateUserKey(key);
        return AjaxResult.success(quotationService.GetQuotationById(id,0));

    }
}