package com.myIsoland.controller.chapterIndex;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.enitity.product.PoemSet;
import com.myIsoland.enitity.product.Poetry;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.PoemContentService;
import com.myIsoland.service.product.PoemSetService;
import com.myIsoland.service.product.PoetryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "诗歌章节推荐界面")
@RequestMapping("PoetryIndex")
public class PoetryIndexController {

    @Autowired
    private PoemSetService poemSetService;

    @Autowired
    private PoetryService poetryService;

    @Autowired
    private PoemContentService poemContentService;
    /**
     *@Author:THINKPAD
     *@Description:获取书籍章节信息
     * @param startIndex
     * @param pageSize
     *@Return:java.lang.Object
     *@Data:22:18 2020/1/29
     **/
    @GetMapping("/readPoemSets")
    public Object ReadLiteratureCharps(int startIndex,int pageSize){
        Map<String,Object> data = new HashMap<>();

        ResultSet<PoemSet> resultSet = poemSetService.GetPoemSetByHotNo(startIndex,pageSize);

        if(startIndex==0) {

            ResultSet<PoemContent> resultSet1 = poemContentService.GetFinRecomPoemContent(DateUtils.getNowDate(),0,0);

            resultSet1.setEntity(CaculateUtils.deletePrefix(resultSet1.getEntity()));

            data.put("result1",resultSet1);

            List<Poetry> poetries = poetryService.GetRecomPoetry(0, 8);

            data.put("list", CaculateUtils.deletePoetryPrefix(poetries));

        }
         resultSet.setList(CaculateUtils.deleteSetsPrefix(resultSet.getList()));

        data.put("result",resultSet);

        return AjaxResult.success(data);
    }
}
