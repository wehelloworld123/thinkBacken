package com.myIsoland.controller.chapterIndex;


import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.product.PaintContent;
import com.myIsoland.enitity.product.PaintingPart;
import com.myIsoland.enums.CreateEnum;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.PaintContentService;
import com.myIsoland.service.product.PaintingPartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "绘画章节推荐界面")
@RequestMapping("PaintIndex")
public class PaintIndexController {
    @Autowired
    private PaintingPartService paintingPartService;

    @Autowired
    private PaintContentService paintContentService;

    /**
     *@Author:THINKPAD
     *@Description:获取绘画章节信息
     * @param startIndex
     * @param pageSize
     *@Return:java.lang.Object
     *@Data:22:18 2020/1/29
     **/
    @GetMapping("/readPaintParts")
    public Object ReadPaintParts(String date,String type,int startIndex,int pageSize){
        Map<String,Object> dataMap = new HashMap<>();
        ResultSet<PaintingPart> resultSet = new ResultSet<>();
        ResultSet<PaintContent> resultSet1 = new ResultSet<>();

        if(Integer.parseInt(type)<100) {
            resultSet = paintingPartService.GetPartByType(CreateEnum.valueOf(type+""),startIndex,pageSize);
        }else{
            resultSet = paintingPartService.GetPartByDateOrNo(DateUtils.parseDate(date),Integer.parseInt(type),startIndex,pageSize);
        }
        resultSet.setList(CaculateUtils.deletePartsPrefix(resultSet.getList()));
        dataMap.put("resultSet",resultSet);
        if(startIndex==0&&type.equals("01")) {
            resultSet1 = paintContentService.GetFinRecomPaintContent(DateUtils.parseDate(date), 0, 4);
            resultSet1.setList(CaculateUtils.deletePaintsPrefix(resultSet1.getList()));
            dataMap.put("resultSet1", resultSet1);
        }
        return AjaxResult.success(resultSet);
    }
}
