package com.myIsoland.controller.debate;


import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.enitity.community.SubjectTopic;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.community.SubjectTopicService;
import com.myIsoland.service.product.LiteratureService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "专题界面")
@RequestMapping("Csubject")
public class CsubjectController {

    @Autowired
    private LiteratureService literatureService;

    @Autowired
    private SubjectTopicService subjectTopicService;

    @Autowired
    private

    /**
     * 获取专栏页面推荐自由创作
     * @return
     */
    @GetMapping("/readSubjectPageData")
    public Object  ReadSubjectPageData() {
        ResultSet<Literature> resultSet = new ResultSet<>();
        List<Literature> literatures = literatureService.GetLiteratureByType(CreateKind.INCLUDED,1000,999999,6);
        List<SubjectTopic> subjectTopics = subjectTopicService.GetSubjectTopicByHot(0,10);
        return AjaxResult.success();
    }

    /**
     * 获取专栏页面推荐自由创作
     * @return
     */
    @GetMapping("/readRecomIncludPro")
    public Object  ReadRecomIncludPro() {
        ResultSet<Literature> resultSet = new ResultSet<>();
        List<Literature> list = literatureService.GetLiteratureByType(CreateKind.INCLUDED,1000,999999,6);
        resultSet.setList(CaculateUtils.deleteLiteraturePrefix(list));
        return AjaxResult.success(resultSet);
    }
}
