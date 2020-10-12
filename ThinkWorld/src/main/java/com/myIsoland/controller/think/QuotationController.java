package com.myIsoland.controller.think;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enitity.think.Quotation;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.think.QuotationService;
import com.myIsoland.shiro.util.ShiroUtils;
import com.myIsoland.util.DateUtils;
import com.myIsoland.util.SnowflakeIdWorker;
import com.myIsoland.util.StringUtils;
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
    public Object ReadQtation(String theoryNo,String date,int start,int limit){
        String userId = ShiroUtils.getUserId();
        ResultSet<Quotation> data = quotationService.GetUserQuotation(userId,theoryNo, DateUtils.parseDate(date),start,limit);
        return AjaxResult.success(data);
    }

    @GetMapping("/readUserQtation")
    public Object ReadUserQtation(String userId,String theoryNo,String date,int start,int limit){
        ResultSet<Quotation> data = quotationService.GetUserQuotation(userId,theoryNo, DateUtils.parseDate(date),start,limit);
        return AjaxResult.success(data);
    }

    @GetMapping("/readQtationDetail")
    public Object ReadQtationDetail(int id){
            return AjaxResult.success(quotationService.GetQuotationById(id,1));
    }



    @PostMapping("/modifyQuotation")
    public Object ModifyQuotation(int id,String question,String content,String explation,int lock){

        Quotation data = new Quotation();
        data.setId(id);
        if(StringUtils.isNotEmpty(question)) {
            data.setQuestion(question);
        }
        if(StringUtils.isNotEmpty(content)) {
            data.setContent(content);
        }
        if(StringUtils.isNotEmpty(explation)) {
            data.setExplation(explation);
        }
        data.setIsLock(lock);
        quotationService.UpdateQuotationById(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());

    }

    @PostMapping("/createQuotation")
    public Object CreateQuotation(String question,String content,String explation,String theoryNo){
        Quotation data = new Quotation(SnowflakeIdWorker.getUUID(),content,explation,theoryNo);
        data.setQuestion(question);
        quotationService.save(data);
        return AjaxResult.success(data);

    }
}
