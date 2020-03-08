package com.myIsoland.controller.creation;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.PoemSet;
import com.myIsoland.enitity.product.Poetry;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.enums.RecomType;
import com.myIsoland.service.product.PoemSetService;
import com.myIsoland.service.product.PoetryService;
import com.myIsoland.service.product.UserCreationService;
import io.swagger.annotations.Api;
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
    @GetMapping("/readInitPotry")
    public Object ReadInitPoetry(int kind, int partner, int views){
        Map<String,Object> data = new HashMap<>();

        List<Poetry> poetries = poetryService.GetPoetryByType(CreateKind.valueOf(kind),partner,views);
        Poetry poetry = poetryService.GetTopPoetry();

        data.put("poetries",CaculateUtils.deletePoetryPrefix(poetries));
        data.put("poetry",CaculateUtils.deletePrefix(poetry));

        return AjaxResult.success(data);

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
    @GetMapping("/readPoetries")
    public Object ReadPaintings(int kind, int partner, int views){
        Map<String,Object> data = new HashMap<>();

        List<Poetry> poetries = poetryService.GetPoetryByType(CreateKind.valueOf(kind),partner,views);
        data.put("poetries",CaculateUtils.deletePoetryPrefix(poetries));

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
    public Object ReadPaintingDetail(String poetryId){
        Map<String,Object> data = new HashMap<>();
        poetryId = ProjectConstant.POETRYPREFIX + poetryId;
        Poetry poetry= poetryService.GetPoetryById(poetryId);
        List<Map<String,Object>> partner = userCreationService.GetCreatPartInfo(poetryId, RecomType.POEMTY);
        data.put("painting", CaculateUtils.deletePrefix(poetry));
        data.put("partner",partner);
        return AjaxResult.success(data);
    }

    /**
     *@Author:THINKPAD
     *@Description:获取诗歌章节信息
     * @param poetryId
     *@Return:java.lang.Object
     *@Data:22:18 2020/1/29
     **/
    @GetMapping("/readPoemSets")
    public Object ReadPaintingParts(String poetryId){
        poetryId = ProjectConstant.POETRYPREFIX + poetryId;
        List<PoemSet>  parts= poemSetService.GetPoemSetByPoetryId(poetryId);
        return AjaxResult.success(CaculateUtils.deleteSetsPrefix(parts));
    }
}
