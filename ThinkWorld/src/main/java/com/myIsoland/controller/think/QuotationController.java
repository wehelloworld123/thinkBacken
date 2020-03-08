package com.myIsoland.controller.think;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enitity.think.Quotation;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.service.think.QuotationService;
import com.myIsoland.shiro.util.ShiroUtils;
import com.myIsoland.util.SnowflakeIdWorker;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "语论界面")
@RequestMapping("Quotation")
public class QuotationController {
    @Autowired
    private QuotationService quotationService;

    @GetMapping("/readQtation")
    public Object ReadQtation(String theoryNo){
        String userId = ShiroUtils.getUserId();
        List<Quotation> data = quotationService.GetUserQuotation(userId,theoryNo);
        System.out.println("test:"+data);
        return AjaxResult.success(data);
    }

    @GetMapping("/readQtationDetail")
    public Object ReadQtationDetail(int id){
            return AjaxResult.success(quotationService.GetQuotationById(id,1));
    }



    @PostMapping("/modifyQuotation")
    public Object ModifyQuotation(int id,String content,String explation,int lock){

        Quotation data = new Quotation();
        data.setId(id);
        data.setContent(content);
        data.setExplation(explation);
        data.setIsLock(lock);
        int i = quotationService.UpdateQuotationById(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());

    }

    @PostMapping("/createQuotation")
    public Object CreateQuotation(String content,String explation,String theoryNo,int lock){
        TsysUser user = ShiroUtils.getUser();
        Quotation data = new Quotation(SnowflakeIdWorker.getUUID(),content,explation,theoryNo);
        data.setIsLock(lock);
         quotationService.SaveQuotation(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());

    }
}
