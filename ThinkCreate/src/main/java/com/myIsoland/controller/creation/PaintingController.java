package com.myIsoland.controller.creation;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enitity.product.Painting;
import com.myIsoland.enitity.product.PaintingPart;
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
     *@Description:读取艺术绘画初始化页面
     * @param kind
     * @param partner
     * @param views
     *@Return:java.lang.Object
     *@Data:14:32 2020/1/31
     **/
    @GetMapping("/readInitPainting")
    public Object ReadInitPainting(int kind, int partner, int views){
        Map<String,Object> data = new HashMap<>();

       List<Painting> paintings = paintingService.GetPaintingByType(CreateKind.valueOf(kind),partner,views);
       Painting painting = paintingService.GetTopPainting();

       data.put("paintings",paintings);
       data.put("painting",painting);

       return AjaxResult.success(data);

    }

    /**
     *@Author:THINKPAD
     *@Description:读取艺术绘画信息
     * @param kind
     * @param partner
     * @param views
     *@Return:java.lang.Object
     *@Data:14:41 2020/1/31
     **/
    @GetMapping("/readPaintings")
    public Object ReadPaintings(int kind, int partner, int views){
        Map<String,Object> data = new HashMap<>();

        List<Painting> paintings = paintingService.GetPaintingByType(CreateKind.valueOf(kind),partner,views);
        data.put("paintings",paintings);

        return AjaxResult.success(data);

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
        List<Map<String,Object>> partner = userCreationService.GetCreatPartInfo(paintId, RecomType.PAINTING);
        data.put("painting", CaculateUtils.deletePrefix(painting));
        data.put("partner",partner);
        return AjaxResult.success(data);
    }

    /**
     *@Author:THINKPAD
     *@Description:获取绘画部分信息
     * @param paintId
     *@Return:java.lang.Object
     *@Data:22:18 2020/1/29
     **/
    @GetMapping("/readPaintingParts")
    public Object ReadPaintingParts(String paintId){
        paintId = ProjectConstant.PAINTINGPREFIX + paintId;
        List<PaintingPart>  parts= paintingPartService.GetPaintingPartByPaintId(paintId);
        return AjaxResult.success(parts);
    }

}
