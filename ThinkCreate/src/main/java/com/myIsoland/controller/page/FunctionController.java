package com.myIsoland.controller.page;


import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.personal.RecomBook;
import com.myIsoland.enitity.personal.Saying;
import com.myIsoland.service.debate.UserTopicService;
import com.myIsoland.service.personal.RecomBookService;
import com.myIsoland.service.personal.SayingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "功能区界面")
@RequestMapping("Function")
public class FunctionController {

    @Autowired
    private RecomBookService recomBookService;

    @Autowired
    private SayingService sayingService;

    @Autowired
    private UserTopicService userTopicService;

    /**
     *@Author:THINKPAD
     *@Description:读取系统推荐信息
     * @param
     *@Return:java.lang.Object
     *@Data:20:58 2020/1/25
     **/
    @RequestMapping("/readRecomInfo")
    public Object ReadRecomInfo(String date){
        Map data = new HashMap<String, Object>();
        try {
            List<RecomBook> recomBook = recomBookService.GetRecomBooks(DateUtils.parseDate(date));
            List<Saying> sayings = sayingService.GetSayingList(DateUtils.parseDate(date));
            data.put("recomBook",recomBook);
            data.put("sayings",sayings);
            return AjaxResult.success(data);
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }

/*    @RequestMapping("/read")
    public void ReadRecom(){
        Map data = new HashMap<String, Object>();
        try {
            List<Map<String,Object>> recomBook = userTopicService.GetUserRankAns();
            System.out.println("show:"+recomBook);
        }catch (Exception e){
            throw  e;
        }
    }*/
}
