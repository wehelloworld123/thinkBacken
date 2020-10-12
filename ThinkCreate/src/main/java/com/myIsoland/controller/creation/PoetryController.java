package com.myIsoland.controller.creation;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.PoemSet;
import com.myIsoland.enitity.product.Poetry;
import com.myIsoland.enums.CreateEnum;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.enums.RecomType;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.PoemSetService;
import com.myIsoland.service.product.PoetryService;
import com.myIsoland.service.product.UserCreationService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "诗歌集接口")
@RequestMapping("Poetry")
public class PoetryController {
    private final Logger logger = LoggerFactory.getLogger(PoetryController.class);

    @Autowired
    private PoetryService poetryService;

    @Autowired
    private UserCreationService userCreationService;

    @Autowired
    private PoemSetService poemSetService;

    /**
     *@Author:THINKPAD
     *@Description:读取诗歌集初始化页面
     * @param kind
     * @param partner
     * @param views
     *@Return:java.lang.Object
     *@Data:14:32 2020/1/31
     **/
    @GetMapping("/readNewPoetry")
    public Object ReadNewPoetry(String kind, String date,int start, int limit){
        ResultSet<Poetry> resultSet = new ResultSet<>();
        logger.debug(kind);
        System.out.println(kind);
        List<Poetry> poetries = poetryService.GetPoetryByDate(CreateEnum.valueof(kind),DateUtils.parseDate(date),start,limit);

        resultSet.setList(CaculateUtils.deletePoetryPrefix(poetries));

        return AjaxResult.success(resultSet);

    }

    /**
     *@Author:THINKPAD
     *@Description:读取诗歌集信息
     * @param kind
     * @param partner
     * @param views
     *@Return:java.lang.Object
     *@Data:14:41 2020/1/31
     **/
    @GetMapping("/readHotPoetries")
    public Object ReadHotPoetries(String kind, int partner, int views,int limit){
        Map<String,Object> data = new HashMap<>();
        logger.info(kind);
        List<Poetry> poetries = poetryService.GetPoetryByType(CreateEnum.valueOf(kind),partner,views,limit);
        data.put("poetries",CaculateUtils.deletePoetryPrefix(poetries));

        return AjaxResult.success(data);

    }

    /**
     *@Author:THINKPAD
     *@Description:读取最新诗歌集初始化页面
     * @param partner
     * @param views
     *@Return:java.lang.Object
     *@Data:14:32 2020/1/31
     **/
    @GetMapping("/readInitNewPoetry")
    public Object ReadInitNewPoetry(String date,int limit){
        Map<String,Object> data = new HashMap<>();

        List<Poetry> poetries = poetryService.GetPoetryByDate(CreateEnum.POEM,DateUtils.parseDate(date),0,limit);
        List<Poetry> brands = poetryService.GetPoetryByDate(CreateEnum.BRAND,DateUtils.parseDate(date),0,limit);
        data.put("poetries",CaculateUtils.deletePoetryPrefix(poetries));
        data.put("brands",CaculateUtils.deletePoetryPrefix(brands));
        return AjaxResult.success(data);

    }

    /**
     *@Author:THINKPAD
     *@Description:读取最新诗歌集初始化页面
     * @param partner
     * @param views
     *@Return:java.lang.Object
     *@Data:14:32 2020/1/31
     **/
    @GetMapping("/readInitHotPoetry")
    public Object ReadInitHotPoetry(int partner, int views,int limit){


        Map<String,Object> data = new HashMap<>();

        List<Poetry> poetries = poetryService.GetPoetryByType(CreateEnum.POEM,partner,views,limit);
        List<Poetry> brands = poetryService.GetPoetryByType(CreateEnum.BRAND,partner,views,limit);
        data.put("poetries",CaculateUtils.deletePoetryPrefix(poetries));
        data.put("brands",CaculateUtils.deletePoetryPrefix(brands));
        return AjaxResult.success(data);
    }

    /**
     *@Author:THINKPAD
     *@Description:根据诗歌集Id获取诗歌详细信息以及参与人员
     * @param poetryId
     *@Return:java.lang.Object
     *@Data:21:33 2020/1/29
     **/
    @GetMapping("/readPoetryDetail")
    public Object ReadPoetryDetail(String poetryId){
        ResultSet<Poetry> resultSet = new ResultSet<>();
        poetryId = ProjectConstant.POETRYPREFIX + poetryId;
        Poetry poetry= poetryService.GetPoetryById(poetryId);
        List<Map<String,Object>> partner = userCreationService.GetCreatPartInfo(poetryId, RecomType.POEMTY,0,10);
        resultSet.setEntity(CaculateUtils.deletePrefix(poetry));
        return AjaxResult.success(resultSet);
    }

    /**
     *@Author:THINKPAD
     *@Description:读取诗歌作品详情
     * @param uid
     *@Return:java.lang.Object
     *@Data:21:33 2020/1/29
     **/
    @GetMapping("/readPoetry")
    public Object ReadPoetry(String uid){
        uid = ProjectConstant.POETRYPREFIX + uid;
        Poetry poetry= poetryService.GetPoetryById(uid);
        return AjaxResult.success(poetry);
    }

    /**
     *@Author:THINKPAD
     *@Description:根据诗歌集Id获取参与人员
     * @param poetryId
     *@Return:java.lang.Object
     *@Data:21:33 2020/1/29
     **/
    @GetMapping("/readPoetryCreators")
    public Object ReadPoetryCreators(String poetryId){
        ResultSet<Map<String,Object>> resultSet = new ResultSet<>();
        poetryId = ProjectConstant.POETRYPREFIX + poetryId;
        List<Map<String,Object>> partner = userCreationService.GetCreatPartInfo(poetryId, RecomType.POEMTY,0,10);
        resultSet.setList(partner);
        return AjaxResult.success(resultSet);
    }
    /**
     *@Author:THINKPAD
     *@Description:获取诗歌章节信息
     * @param poetryId
     *@Return:java.lang.Object
     *@Data:22:18 2020/1/29
     **/
    @GetMapping("/readPoemSets")
    public Object ReadPoemSets(String poetryId,int start,int limit){
        System.out.println(poetryId);
        System.out.println(start);
        System.out.println(limit);
        poetryId = ProjectConstant.POETRYPREFIX + poetryId;
        List<PoemSet>  parts= poemSetService.GetPoemSetsByPoetryId(poetryId,start,limit,false);
        return AjaxResult.success(CaculateUtils.deleteSetsPrefix(parts));
    }

    /**
     *@Author:THINKPAD
     *@Description:获取诗歌主创人员信息
     * @param poetryId
     *@Return:java.lang.Object
     *@Data:22:18 2020/1/29
     **/
    @GetMapping("/readPoemAuthors")
    public Object ReadPoemAuthors(String poetryId, String date,int start,int limit){
        poetryId = ProjectConstant.POETRYPREFIX + poetryId;
        List<Map<String,Object>>  list= userCreationService.GetAuthorInfoByPoetryId(poetryId,DateUtils.parseDate(date),start,limit);
        for (Map<String,Object> map : list){
            map.put("userId",CaculateUtils.translateId(ProjectConstant.USERPREFIX,(String) map.get("userId")));
        }
        return AjaxResult.success(list);
    }

    /**
     *@Author:THINKPAD
     *@Description:搜索绘画或者章节信息
     * @param keyword
     * @param startIndex
     * @param pageSize
     * @param type
     *@Return:java.lang.Object
     *@Data:22:18 2020/1/29
     **/
    @GetMapping("/search")
    public Object Search(String keyword,int startIndex,int pageSize,int type){
        if(type==0) {
            List<Poetry> list = poetryService.QueryPoetryByKey(keyword, startIndex, pageSize);
            return AjaxResult.success(CaculateUtils.deletePoetryPrefix(list));
        }else {
            List<PoemSet> list = poemSetService.QueryPoetrySetByKey(keyword,startIndex,pageSize);
            return AjaxResult.success(CaculateUtils.deleteSetsPrefix(list));
        }
    }
}
