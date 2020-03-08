package com.myIsoland.controller.creation;

import com.myIsoland.common.component.SingleParamException;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.product.Recommend;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.enums.RecomType;
import com.myIsoland.service.product.LiterContentService;
import com.myIsoland.service.product.PaintContentService;
import com.myIsoland.service.product.PoemContentService;
import com.myIsoland.service.product.RecommendService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "作品详情界面")
@RequestMapping("CreationDetail")
public class CreationDetailController {
    @Autowired
    private LiterContentService literContentService;
    @Autowired
    private PaintContentService paintContentService;
    @Autowired
    private PoemContentService poemContentService;
    @Autowired
    private RecommendService recommendService;


    /**
     *@Author:THINKPAD
     *@Description:根据创作编号获取创作具体信息
     * @param no
     *@Return:java.lang.Object
     *@Data:21:10 2020/1/30
     **/
    @GetMapping("/readCreationDetail")
    public Object readCreationDetail(String no,int type) throws SingleParamException {
        if(RecomType.LITERATURE.equals(type)){
            no = ProjectConstant.CLITERPREFIX + no;
            return AjaxResult.success(CaculateUtils.deletePrefix(literContentService.GetLiterContentDetail(no)));
        }else if(RecomType.PAINTING.equals(type)){
            no = ProjectConstant.CPAINTINGPREFIX + no;
            return AjaxResult.success(paintContentService.GetPaintContentById(no));
        }else if(RecomType.POEMTY.equals(type)){
            no = ProjectConstant.CPOETRYPREFIX + no;
            return AjaxResult.success(poemContentService.GetPoemContentById(no));
        }else
            throw new  SingleParamException("类型查找错误");
    }


    /**
     *@Author:THINKPAD
     *@Description:创建用户评论
     * @param content
     * @param proId
     * @param type
     *@Return:java.lang.Object
     *@Data:23:51 2020/1/30
     **/
    @PostMapping("/createRecommend")
    public Object CreateRecommend(String content,String proId,int kind){
        Recommend data = new Recommend();
        data.setConent(content);
        data.setContenId(proId);
        data.setKind(kind);
        data.setCreator(ShiroUtils.getUser().getUsername());
        data.setCreateAvat(ShiroUtils.getUser().getAvatar());
        recommendService.SaveRecomment(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
    /**
     *@Author:THINKPAD
     *@Description:读取评论
     * @param no
     * @param date
     * @param type
     * @param start
     *@Return:java.lang.Object
     *@Data:0:02 2020/1/31
     **/
    @GetMapping("/readRecommends")
    public Object ReadRecommends(String no,String date,int type,int start){
        no = ProjectConstant.CLITERPREFIX + no;
        List<Recommend> data = recommendService.GetRecommentByDate
                (no, DateUtils.parseDate(date),RecomType.valueOf(type),start);
        return AjaxResult.success(data);
    }

    @PostMapping("/modifyRecomLike")
    public Object ModifyRecomLike(Long id){
        recommendService.UpdateLikeSts(ShiroUtils.getUserId(),id);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}
