package com.myIsoland.controller.community;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.community.SubjectTopic;
import com.myIsoland.service.community.DisscussService;
import com.myIsoland.service.community.SubjectTopicService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 讨论controller
 * @author xuyong
 * @date: 2019年11月20日 下午4:23:50
 */
@RestController
@Api(value = "专题信息")
@RequestMapping("SubjectTopic")
public class SubjectTopicController {
    @Autowired
    private DisscussService disscussService;
    @Autowired
    private SubjectTopicService subjectTopicService;
    @Autowired
    private RedisCacheService redisCacheService;



    /**
     *@Author:THINKPAD
     *@Description:读取专题列表信息
     *@Return:java.lang.Object
     *@Data:23:39 2019/11/24
     **/
    @GetMapping("/readSubjectList")
    public Object ReadSubjectList(){
        List<SubjectTopic> list = subjectTopicService.GetSubjectTopicByHot(0,10);
        return AjaxResult.success();
    }
}
