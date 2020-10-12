package com.myIsoland.controller.chapterIndex;


import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.product.LiterCharpt;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.LiterCharpService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "文学章节推荐界面")
@RequestMapping("LiteratureIndex")
public class LiteratureIndexController {
    @Autowired
    private LiterCharpService literCharpService;

    /**
     *@Author:THINKPAD
     *@Description:获取书籍章节信息
     * @param type
     *@Return:java.lang.Object
     *@Data:22:18 2020/1/29
     **/
    @GetMapping("/readLiteratureChaps")
    public Object ReadLiteratureCharps(int type,String date,int startIndex,int pageSize){
        ResultSet<LiterCharpt> resultSet = new ResultSet<>();
        if(type<100) {
            resultSet = literCharpService.GetChapterByType(CreateKind.valueOf(type),startIndex,pageSize);
        }else{
            resultSet = literCharpService.GetChapterByDateOrNo(DateUtils.parseDate(date),type,startIndex,pageSize);
        }
        resultSet.setList(CaculateUtils.deleteChartpsPrefix(resultSet.getList()));
        return AjaxResult.success(resultSet);
    }
}
