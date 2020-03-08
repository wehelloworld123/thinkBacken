package com.myIsoland.controller.creation;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.enitity.product.PoemSet;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.service.product.PoemContentService;
import com.myIsoland.service.product.PoemSetService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "诗歌创作接口")
@RequestMapping("PoemCreation")
public class PoemCreationController {
    @Autowired
    private PoemContentService poemContentService;
    @Autowired
    private PoemSetService poemSetService;
    /**
     *@Author:THINKPAD
     *@Description:初始化诗歌创作信息
     * @param charptId
     * @param date
     *@Return:java.lang.Object
     *@Data:16:43 2020/1/30
     **/
    @GetMapping("/readPoemSetDetail")
    public Object ReadPoemSetDetail(Long charptId, String date){
        Map<String,Object> data = new HashMap<>();
        String userId = ShiroUtils.getUserId();
        PoemSet chapter = poemSetService.GetPoemSetDetail(charptId);
        List<PoemContent> creations = poemContentService.GetHotPoemContent(userId,charptId);
        List<String> arr = new LinkedList<>();
        for (PoemContent content : creations){
            arr.add(ProjectConstant.CPOETRYPREFIX + content.getNo());
        }
        List<PoemContent> contents = poemContentService.GetContentsOrderByDate(userId,charptId, DateUtils.parseDate(date),arr);
        data.put("chapter", CaculateUtils.deletePrefix(chapter));
        data.put("creations",CaculateUtils.deletePoemsPrefix(creations));
        data.put("contents",CaculateUtils.deletePoemsPrefix(contents));
        return AjaxResult.success(data);
    }

    /**
     *@Author:THINKPAD
     *@Description:更新内容点赞喜欢
     * @param no
     *@Return:java.lang.Object
     *@Data:13:42 2020/2/4
     **/
    @GetMapping("/modifyLikesSts")
    public Object ModifyLikesSts(String no){
        String userId = ShiroUtils.getUserId();
        no = ProjectConstant.CLITERPREFIX + no;
        poemContentService.UpdateLikeSts(userId,no);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }

    /**
     *@Author:THINKPAD
     *@Description:根据日期获取接下来诗歌作品
     * @param charptId
     * @param date
     *@Return:java.lang.Object
     *@Data:17:19 2020/1/30
     **/
    @GetMapping("/readNextPoemPro")
    public Object ReadNextPoemPro(Long charptId,String date){
        List<PoemContent> contents = poemContentService.GetContentsOrderByDate
                (ShiroUtils.getUserId(),charptId,DateUtils.parseDate(date),new LinkedList<>());
        return AjaxResult.success(CaculateUtils.deletePoemsPrefix(contents));

    }

    /**
     *@Author:THINKPAD
     *@Description:创建绘画创作作品
     * @param title
     * @param chartId
     * @param poetryName
     * @param partName
     * @param secName
     *@Return:java.lang.Object
     *@Data:23:38 2020/1/30
     **/
    @PostMapping("/createPoemContent")
    public Object CreatePoemContent(String brand,String title,Long chartId,String poetryName,String charptName,String secName){
        PoemContent data = new PoemContent();
        data.setNo(ProjectConstant.CPOETRYPREFIX+ SnowflakeIdWorker.getUUID());
        data.setBrand(brand);
        data.setTitle(title);
        data.setCharpId(chartId);
        data.setPoetryName(poetryName);
        data.setCharpName(charptName);
        data.setSecName(secName);
        poemContentService.save(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}
