package com.myIsoland.controller.creation;


import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.file.FileUploadUtils;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.PaintContent;
import com.myIsoland.enitity.product.Painting;
import com.myIsoland.enitity.product.PaintingPart;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.service.product.PaintContentService;
import com.myIsoland.service.product.PaintingPartService;
import com.myIsoland.service.product.PaintingService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "艺术绘画创作界面")
@RequestMapping("PaintCreation")
public class PaintCreationController {
    @Autowired
    private PaintContentService paintContentService;
    @Autowired
    private PaintingPartService paintingPartService;
    /**
     *@Author:THINKPAD
     *@Description:初始化艺术绘图创作信息
     * @param paintId
     * @param date
     *@Return:java.lang.Object
     *@Data:16:43 2020/1/30
     **/
    @GetMapping("/readPaintingPro")
    public Object ReadPaintingPro(Long paintId, String date){
        Map<String,Object> data = new HashMap<>();
        String userId = ShiroUtils.getUserId();
        PaintingPart part = paintingPartService.GetCharptDetail(paintId);
        List<PaintContent> creations = paintContentService.GetHotContent(userId,paintId);
        List<String> arr = new LinkedList<>();
        for (PaintContent content : creations){
            arr.add(ProjectConstant.CLITERPREFIX + content.getNo());
        }
        List<PaintContent> contents = paintContentService.GetContentsOrderByDate(userId,paintId, DateUtils.parseDate(date),arr);
        data.put("charpt", CaculateUtils.deletePrefix(part));
        data.put("creations",CaculateUtils.deletePaintsPrefix(creations));
        data.put("contents",CaculateUtils.deletePaintsPrefix(contents));
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
        paintContentService.UpdateLikeSts(userId,no);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }

    /**
     *@Author:THINKPAD
     *@Description:根据日期获取接下来绘画作品
     * @param charptId
     * @param date
     *@Return:java.lang.Object
     *@Data:17:19 2020/1/30
     **/
    @GetMapping("/readNextLiteraturePro")
    public Object ReadNextLiteraturePro(Long charptId,String date){
        List<PaintContent> contents = paintContentService.GetContentsOrderByDate
                (ShiroUtils.getUserId(),charptId,DateUtils.parseDate(date),new LinkedList<String>());
        return AjaxResult.success(CaculateUtils.deletePaintsPrefix(contents));

    }

    /**
     *@Author:THINKPAD
     *@Description:创建绘画创作作品
     * @param title
     * @param partId
     * @param paintName
     * @param partName
     * @param secName
     * @param imagefile
     *@Return:java.lang.Object
     *@Data:23:38 2020/1/30
     **/
    @PostMapping("/createPaintContent")
    public Object CreateLiterContent(String title,Long partId,String paintName,String partName,String secName,
                                     @RequestParam(value = "imagefile") MultipartFile imagefile) throws IOException {
        String image = FileUploadUtils.upload(imagefile);
        PaintContent data = new PaintContent();
        data.setNo(ProjectConstant.CPAINTINGPREFIX+ SnowflakeIdWorker.getUUID());
        data.setTitle(title);
        data.setImage(image);
        data.setPartId(partId);
        data.setPaintName(paintName);
        data.setPartName(partName);
        data.setSecName(secName);
        paintContentService.save(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}
