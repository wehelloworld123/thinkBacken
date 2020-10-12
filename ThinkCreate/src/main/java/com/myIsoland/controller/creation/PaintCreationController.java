package com.myIsoland.controller.creation;


import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.file.FileUploadUtils;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.constant.RedisConstant;
import com.myIsoland.enitity.product.PaintContent;
import com.myIsoland.enitity.product.Painting;
import com.myIsoland.enitity.product.PaintingPart;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.model.ResultSet;
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

    @Autowired
    private RedisCacheService redisCacheService;
    /**
     *@Author:THINKPAD
     *@Description:初始化艺术绘图创作信息
     * @param partId
     * @param date
     *@Return:java.lang.Object
     *@Data:16:43 2020/1/30
     **/
    @GetMapping("/readPaintingPro")
    public Object ReadPaintingPro(Long partId, String date,int startIndex,int pageSize){
        Map<String,Object> data = new HashMap<>();
       // String userId = ShiroUtils.getUserId();
        PaintingPart part = paintingPartService.GetCharptDetail(partId);

        if(part.getFinish()==1){
            PaintContent content = paintContentService.GetAdoptContent(partId);
            data.put("creation",CaculateUtils.deletePrefix(content));
        }
 /*       ResultSet<PaintContent> resultSet = paintContentService.GetContentsByLikes
            (null,partId,startIndex,pageSize);
        resultSet.setList(CaculateUtils.deletePaintsPrefix (resultSet.getList()));
        List<PaintContent> creations = paintContentService.GetHotContent(partId,0,3);
        data.put("result",CaculateUtils.deletePaintsPrefix(creations));*/

     //   List<PaintContent> contents = paintContentService.GetContentsOrderByDate(userId,partId, DateUtils.parseDate(date),startIndex,pageSize,arr);
        data.put("chapter", CaculateUtils.deletePrefix(part));
     //   data.put("contents",CaculateUtils.deletePaintsPrefix(contents));
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
    @GetMapping("/readPaintContentDetail")
    public Object ReadPaintContentDetail(String no, Boolean isLogin){
        no = ProjectConstant.CPAINTINGPREFIX + no;
        String userId = null;
        if(isLogin){
            userId = ShiroUtils.getUserId();
        }

        PaintContent poemContent = paintContentService.GetPaintContentById(no,userId);

        return AjaxResult.success(CaculateUtils.deletePrefix(poemContent));
    }


    /**
     *@Author:THINKPAD
     *@Description:更新内容点赞喜欢
     * @param no
     *@Return:java.lang.Object
     *@Data:13:42 2020/2/4
     **/
    @GetMapping("/modifyLikesSts")
    public Object ModifyLikesSts(String no,int type){
        String userId = ShiroUtils.getUserId();
        no = ProjectConstant.CPAINTINGPREFIX + no;
        paintContentService.UpdateLikeSts(userId,no);

        if (type==1){
            redisCacheService.setHashValue(RedisConstant.CPAINT+no,userId,"1");
            System.out.println(redisCacheService.getHashMap(RedisConstant.CPOETRY+no));
        }else if(type==0){
            Boolean b = redisCacheService.hashFieldExist(RedisConstant.CPAINT+no,userId);
            if(b){
                if(redisCacheService.delHashField(RedisConstant.CPAINT+no,userId).equals(0)){
                    return AjaxResult.error(CodeEnum.REDIS_EXCEPTION.getCode(),CodeEnum.REDIS_EXCEPTION.getMessage(),null);
                }
            }else {
                paintContentService.DelLikeSts(userId,no);
            }
        }
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }

    /**
     *@Author:THINKPAD
     *@Description:根据日期获取接下来绘画作品
     * @param partId
     * @param date
     *@Return:java.lang.Object
     *@Data:17:19 2020/1/30
     **/
    @GetMapping("/readPaintProByDate")
    public Object ReadPaintProByDate(Long partId,String date,int startIndex,int pageSize){
        ResultSet<PaintContent> resultSet = paintContentService.GetContentsOrderByDate
                (null,partId,DateUtils.parseDate(date),startIndex,pageSize,new LinkedList<String>());
        resultSet.setList(CaculateUtils.deletePaintsPrefix (resultSet.getList()));
        return AjaxResult.success(resultSet);

    }

    /**
     *@Author:THINKPAD
     *@Description:根据热度获取接下来绘画作品
     * @param partId
     * @param date
     *@Return:java.lang.Object
     *@Data:17:19 2020/1/30
     **/
    @GetMapping("/readRecomPaintPro")
    public Object ReadRecomPaintPro(Long partId,int startIndex,int pageSize){
        ResultSet<PaintContent> resultSet = paintContentService.GetContentsByLikes
                (null,partId,startIndex,pageSize);
        resultSet.setList(CaculateUtils.deletePaintsPrefix (resultSet.getList()));
        return AjaxResult.success(resultSet);

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
