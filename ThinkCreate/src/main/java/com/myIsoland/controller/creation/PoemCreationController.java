package com.myIsoland.controller.creation;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.constant.RedisConstant;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.enitity.product.PoemSet;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.model.ResultSet;
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
    @Autowired
    private RedisCacheService redisCacheService;
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
        PoemSet poemSet = poemSetService.GetPoemSetDetail(charptId);
        if(poemSet.getFinish()==1){
            PoemContent content = poemContentService.GetAdoptPoemContent(charptId);
            data.put("creation",CaculateUtils.deletePrefix(content));
        }
/*        List<PoemContent> creations = poemContentService.GetHotPoemContent(null,charptId);
        data.put("creations",CaculateUtils.deletePoemsPrefix(creations));*/

        data.put("chapter", CaculateUtils.deletePrefix(poemSet));
        return AjaxResult.success(data);
    }



    /**
     *@Author:THINKPAD
     *@Description:初始化诗歌创作信息
     * @param no
     * @param isLogin
     *@Return:java.lang.Object
     *@Data:16:43 2020/1/30
     **/
    @GetMapping("/readPoemContentDetail")
    public Object ReadPoemContentDetail(String no, Boolean isLogin){
        no = ProjectConstant.CPOETRYPREFIX + no;
        String userId = null;
        if(isLogin){
            userId = ShiroUtils.getUserId();
        }

        PoemContent poemContent = poemContentService.GetPoemContentById(no,userId);

        return AjaxResult.success(CaculateUtils.deletePrefix(poemContent));
    }

    /**
     *@Author:THINKPAD
     *@Description:更新内容点赞喜欢
     * @param no
     *@Return:java.lang.Object
     *@Data:13:42 2020/2/4
     **/
    @PostMapping("/modifyLikesSts")
    public Object ModifyLikesSts(String no,int type){
        String userId = ShiroUtils.getUserId();
        no = ProjectConstant.CPOETRYPREFIX + no;

        if (type==1){
            redisCacheService.setHashValue(RedisConstant.CPOETRY+no,userId,"1");
            System.out.println(redisCacheService.getHashMap(RedisConstant.CPOETRY+no));
        }else if(type==0){
            Boolean b = redisCacheService.hashFieldExist(RedisConstant.CPOETRY+no,userId);
            if(b){
                if(redisCacheService.delHashField(RedisConstant.CPOETRY+no,userId).equals(0)){
                    return AjaxResult.error(CodeEnum.REDIS_EXCEPTION.getCode(),CodeEnum.REDIS_EXCEPTION.getMessage(),null);
                }
            }else {
                poemContentService.DelLikeSts(userId,no);
            }
        }
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
    public Object ReadNextPoemPro(Long charptId,String date,int start,int limit){
        ResultSet<PoemContent> resultSet = poemContentService.GetContentsOrderByDate
                (charptId,DateUtils.parseDate(date),start,limit,new LinkedList<>());
        resultSet.setList(CaculateUtils.deletePoemsPrefix(resultSet.getList()));
        return AjaxResult.success(resultSet);

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
    public Object CreatePoemContent(String brand,String title,String content,Long id){
        Map<String,Object> map = poemSetService.GetPoetryBySetId(id);
        PoemContent data = new PoemContent();
        data.setNo(ProjectConstant.CPOETRYPREFIX+ SnowflakeIdWorker.getUUID());
        data.setBrand(brand);
        data.setTitle(title);
        data.setCharpId(id);
        data.setContent(content);
        data.setPoetryName((String) map.get("poetryName"));
        data.setCharpName((String) map.get("charptName"));
        data.setSecName((String) map.get("secName"));
        poemContentService.save(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}
