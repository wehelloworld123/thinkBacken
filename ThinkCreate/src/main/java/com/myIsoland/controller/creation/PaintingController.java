package com.myIsoland.controller.creation;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enitity.product.Painting;
import com.myIsoland.enitity.product.PaintingPart;
import com.myIsoland.enums.CreateEnum;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.enums.RecomType;
import com.myIsoland.service.product.PaintingPartService;
import com.myIsoland.service.product.PaintingService;
import com.myIsoland.service.product.UserCreationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "艺术绘画界面")
@RequestMapping("Painting")
public class PaintingController {
    @Autowired
    private PaintingService paintingService;

    @Autowired
    private UserCreationService userCreationService;

    @Autowired
    private PaintingPartService paintingPartService;

    /**
     *@Author:THINKPAD
     *@Description:读取艺术绘画信息
     * @param kind
     * @param partner
     * @param views
     * @param limit
     *@Return:java.lang.Object
     *@Data:14:41 2020/1/31
     **/
    @GetMapping("/readPaintings")
    public Object ReadPaintings(String kind, int partner, int views,int start,int limit){
        return AjaxResult.success(paintingService.GetPaintingByType(CreateEnum.valueOf(kind),partner,views,start,limit));
    }

    /**
     *@Author:THINKPAD
     *@Description:读取新发布艺术绘画信息
     * @param date
     * @param kind
     * @param start
     * @param limit
     *@Return:java.lang.Object
     *@Data:14:41 2020/1/31
     **/
    @GetMapping("/readNewPubPaintings")
    public Object ReadNewPubPaintings(String date, int kind, int start,int limit){
        return AjaxResult.success(paintingService.GetPaintingByDate(CreateEnum.valueOf(kind+""), DateUtils.parseDate(date),start,limit));
    }

    /**
     *@Author:THINKPAD
     *@Description:根据绘画Id获取绘画详细信息以及参与人员
     * @param paintId
     *@Return:java.lang.Object
     *@Data:21:33 2020/1/29
     **/
    @GetMapping("/readPaintingDetail")
    public Object ReadPaintingDetail(String paintId){
        Map<String,Object> data = new HashMap<>();
        paintId = ProjectConstant.PAINTINGPREFIX + paintId;
        Painting painting= paintingService.GetPaintingById(paintId);
        List<Map<String,Object>> partner = userCreationService.GetCreatPartInfo(paintId, RecomType.PAINTING,0,20);
        data.put("painting", CaculateUtils.deletePrefix(painting));
        data.put("partner",partner);
        return AjaxResult.success(data);
    }

    /**
     *@Author:THINKPAD
     *@Description:读取绘画作品详情
     * @param uid
     *@Return:java.lang.Object
     *@Data:21:33 2020/1/29
     **/
    @GetMapping("/readPainting")
    public Object ReadPainting(String uid){
        uid = ProjectConstant.PAINTINGPREFIX + uid;
        Painting painting= paintingService.GetPaintingById(uid);
        return AjaxResult.success(painting);
    }

    @GetMapping("/readPaintingCreators")
    public Object ReadPaintingCreators(String paintId){
        List<Map<String,Object>> partner = userCreationService.GetCreatPartInfo(paintId, RecomType.PAINTING,0,20);
        return AjaxResult.success(partner);
    }

    /**
     *@Author:THINKPAD
     *@Description:获取绘画部分信息
     * @param id
     *@Return:java.lang.Object
     *@Data:22:18 2020/1/29
     **/
    @GetMapping("/readPaintingParts")
    public Object ReadPaintingParts(String id){
        id = ProjectConstant.PAINTINGPREFIX + id;
        List<PaintingPart>  parts= paintingPartService.GetPaintingPartByPaintId(id);
        return AjaxResult.success(parts);
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
            List<Painting> list = paintingService.QueryPaintingByKey(keyword, startIndex, pageSize);
            return AjaxResult.success(CaculateUtils.deletePaintingPrefix(list));
        }else {
            List<PaintingPart> list = paintingPartService.QueryPaintingPartByKey(keyword,startIndex,pageSize);
            return AjaxResult.success(CaculateUtils.deletePartsPrefix(list));
        }
    }
}
