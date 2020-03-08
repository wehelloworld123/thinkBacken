package com.myIsoland.service.evaluate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.evaluate.Report;
import com.myIsoland.model.ReportModel;

import java.util.List;

public interface ReportService extends IService<Report> {

    /**
     *@Author:THINKPAD
     *@Description:保存报告
     * @param data
     *@Return:int
     *@Data:16:48 2019/11/17
     **/
    int SaveReport(Report data);

    /**
     *@Author:THINKPAD
     *@Description:获取报告
     * @param userId
     *@Return:java.util.List<com.myIsoland.enitity.evaluate.Report>
     *@Data:16:58 2019/11/17
     **/
    List<Report> GetReports(String userId, int page);

    /**
     *@Author:THINKPAD
     *@Description:更加id获取报告内容
     * @param id
     *@Return:com.myIsoland.enitity.evaluate.Report
     *@Data:20:15 2019/11/18
     **/
    Report GetReportById(String id);
}
