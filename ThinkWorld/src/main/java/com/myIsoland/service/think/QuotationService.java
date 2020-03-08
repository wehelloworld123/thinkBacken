package com.myIsoland.service.think;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.think.Quotation;

import java.util.List;

public interface QuotationService extends IService<Quotation> {
    /**
     *@Author:THINKPAD
     *@Description:根据用户id和理论id获取语录数据
     * @param userId
     * @param theoryNo
     *@Return:java.util.List<com.myIsoland.enitity.think.Quotation>
     *@Data:20:02 2019/12/29
     **/
    List<Quotation> GetQuotationByUserId(String userId,String theoryNo,int lock);

    /**
     *@Author:THINKPAD
     *@Description:获取用户理论语录数据
     * @param userId
     * @param theoryNo
     *@Return:java.util.List<com.myIsoland.enitity.think.Quotation>
     *@Data:20:02 2019/12/29
     **/
    List<Quotation> GetUserQuotation(String userId,String theoryNo);



    /**
     *@Author:THINKPAD
     *@Description:todo
     * @param userId
     * @param theoryNo
     * @param page
     *@Return:java.util.List<com.myIsoland.enitity.think.Quotation>
     *@Data:0:03 2020/1/11
     **/
    List<Quotation> GetUserQuotation(String userId,String theoryNo,int page);
    /**
     *@Author:THINKPAD
     *@Description:根据id获取语论信息
     * @param id,lock
     *@Return:com.myIsoland.enitity.think.Quotation
     *@Data:20:37 2019/12/29
     **/
    Quotation GetQuotationById(int id,int lock);

    /**
     *@Author:THINKPAD
     *@Description:存入语论
     * @param data
     *@Return:int
     *@Data:20:45 2019/12/29
     **/
    int SaveQuotation(Quotation data);

    /**
     *@Author:THINKPAD
     *@Description:根据id更新语论
     * @param data
     *@Return:int
     *@Data:20:53 2019/12/29
     **/
    int UpdateQuotationById(Quotation data);

    /**
     *@Author:THINKPAD
     *@Description:更新语论浏览
     * @param id
     *@Return:int
     *@Data:21:43 2019/12/29
     **/
    int UpdateQuotationView(int id);
}
