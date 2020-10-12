package com.myIsoland.controller.debate;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.community.SubjectTopic;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.community.SubjectTopicService;
import com.myIsoland.service.debate.TopicService;
import com.myIsoland.service.product.LiteratureService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "专题界面")
@RequestMapping("Subject")
public class SubjectController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private SubjectTopicService subjectTopicService;
    @Autowired
    private LiteratureService literatureService;

    /**
     * 获取专栏页面初始化数据
     *
     * @return
     */
    @GetMapping("/readInitPageData")
    public Object ReadInitPageData() {

        Map<String, Object> data = new HashMap<>();
        /**
         *
         * 专题列表信息
         */
        List<SubjectTopic> subjectTopics = subjectTopicService.GetSubjectTopicByHot(0, 10);
        /**********
         * Step 1
         * 获取本期论点
         **********/
        Topic topic = topicService.GetCurtTopic();
        String year;
        int times = 0;
        if (topic.getPeriod() == 1) {
            year = (Integer.parseInt(topic.getYear()) - 1) + "";
            times = 12;
        } else {
            year = topic.getYear();
            times = topic.getPeriod();
        }
        /**********
         * Step 2
         * 获取上期榜单
         **********/
        List<Map<String, Object>> winlist = topicService.GetLastThree(year, times);

        List<Topic> topicList = topicService.GetHotTopic(0, 3);
        /**
         * Step 3
         * 获取自由创作信息
         */
        List<Literature> booklist = literatureService.GetLiteratureByType(CreateKind.INCLUDED, 1000, 999999, 6);
        data.put("subjectTopics", subjectTopics);
        data.put("topic", topic);
        data.put("winlist", winlist);
        data.put("topicList", topicList);
        data.put("booklist", booklist);
        return AjaxResult.success(data);
    }

    /**
     * 获取专栏页面初始化数据
     *
     * @return
     */
    @GetMapping("/readSubjectBookList")
    public Object ReadSubjectBookList(String date,String source,int startIndex,int pageSize) {
        ResultSet<Map<String,Object>> resultSet = new ResultSet<>();
        resultSet = literatureService.GetSubjectBookList(DateUtils.parseDate(date),source,startIndex,pageSize);
        return resultSet;
    }
}
