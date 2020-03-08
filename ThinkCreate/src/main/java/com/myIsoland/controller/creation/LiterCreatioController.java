package com.myIsoland.controller.creation;

import cn.hutool.core.lang.Snowflake;
import com.myIsoland.common.domain.AjaxResult;

import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.product.LiterCharpt;
import com.myIsoland.enitity.product.LiterContent;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.service.product.LiterCharpService;
import com.myIsoland.service.product.LiterCharpServiceImpl;
import com.myIsoland.service.product.LiterContentService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;
import java.util.*;

@RestController
@Api(value = "文学创作界面")
@RequestMapping("LiterCreation")
public class LiterCreatioController {
    @Autowired
    private LiterContentService literContentService;
    @Autowired
    private LiterCharpService literCharpService;
    /**
     *@Author:THINKPAD
     *@Description:初始化文学创作信息
     * @param charptId
     * @param date
     *@Return:java.lang.Object
     *@Data:16:43 2020/1/30
     **/
    @GetMapping("/readLiteraturePro")
    public Object ReadLiteraturePro(Long charptId, String date){
        Map<String,Object> data = new HashMap<>();
        String userId = ShiroUtils.getUserId();
        LiterCharpt charpt = literCharpService.GetCharptDetail(charptId);
        LiterContent content;
        List<LiterContent> creations = null;
        List<String> arr = new LinkedList<>();
        if(charpt.getFinish()==1){
            content = literContentService.GetAdoptContent(charptId);
            data.put("content",CaculateUtils.deletePrefix(content));
        }else {
            creations = literContentService.GetHotContent(userId,charptId);
            for (LiterContent content1 : creations){
                arr.add(content1.getNo());
            }
            data.put("creations",CaculateUtils.deleteListPrefix(creations));
        }

        data.put("charpt",CaculateUtils.deletePrefix(charpt));

        return AjaxResult.success(data);

    }

    /**
     *@Author:THINKPAD
     *@Description:初始化文学创作信息
     * @param charptId
     * @param date
     *@Return:java.lang.Object
     *@Data:16:43 2020/1/30
     **/
    @GetMapping("/readNewLiterPro")
    public Object ReadNewLiterPro(Long charptId, String date){
        Map<String,Object> data = new HashMap<>();
        String userId = ShiroUtils.getUserId();
        List<LiterContent> contents = literContentService.GetContentsOrderByDate(userId,charptId, DateUtils.parseDate(date),new ArrayList<>());
        data.put("contents",CaculateUtils.deleteListPrefix(contents));

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
        literContentService.UpdateLikeSts(userId,no);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
    /**
     *@Author:THINKPAD
     *@Description:根据日期获取接下来文学作品
     * @param charptId
     * @param date
     *@Return:java.lang.Object
     *@Data:17:19 2020/1/30
     **/
    @GetMapping("/readNextLiteraturePro")
    public Object ReadNextLiteraturePro(Long charptId,String date){
       List<LiterContent> contents = literContentService.GetContentsOrderByDate
                (ShiroUtils.getUserId(),charptId,DateUtils.parseDate(date),new LinkedList<String>());
        return AjaxResult.success(CaculateUtils.deleteListPrefix(contents));

    }

    /**
     *@Author:THINKPAD
     *@Description:创建文学创作作品
     * @param content
     * @param charpId
     * @param bookName
     * @param charpName
     * @param secName
     *@Return:java.lang.Object
     *@Data:23:38 2020/1/30
     **/
    @PostMapping("/createLiterContent")
    public Object CreateLiterContent(String content,Long charpId,String bookName,String charpName,String secName){
        LiterContent data = new LiterContent();
        data.setNo(ProjectConstant.CLITERPREFIX+ SnowflakeIdWorker.getUUID());
        data.setContent(content);
        data.setSummary(CaculateUtils.subStr(content,400));
        data.setCharpId(charpId);
        data.setBookName(bookName);
        data.setCharpName(charpName);
        data.setSecName(secName);
        literContentService.SaveLiterContent(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }

}