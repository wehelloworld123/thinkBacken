package com.myIsoland.controller.creation;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.component.SingleParamException;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.product.Recommend;
import com.myIsoland.enitity.system.TsysUser;
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
    @Autowired
    private RedisCacheService redisCacheService;


    /**
     *@Author:THINKPAD
     *@Description:根据创作编号获取创作具体信息
     * @param no
     *@Return:java.lang.Object
     *@Data:21:10 2020/1/30
     **/
    @GetMapping("/readCreationDetail")
    public Object readCreationDetail(String no,int type,Boolean isLogin) throws SingleParamException {
        String userId = null;
        if(isLogin){
            userId = ShiroUtils.getUserId();
        }
        if(RecomType.LITERATURE.getValue()==type){
            no = ProjectConstant.CLITERPREFIX + no;
            return AjaxResult.success(CaculateUtils.deletePrefix(literContentService.GetLiterContentDetail(userId,no)));
        }else if(RecomType.PAINTING.getValue()==type){
            no = ProjectConstant.CPAINTINGPREFIX + no;
            return AjaxResult.success(paintContentService.GetPaintContentById(no,null));
        }else if(RecomType.POEMTY.getValue()==type){
            no = ProjectConstant.CPOETRYPREFIX + no;
            return AjaxResult.success(poemContentService.GetPoemContentById(no,userId));
        }else
            throw new  SingleParamException("类型查找错误");
    }

    /**
     *@Author:THINKPAD
     *@Description:用户点赞创作内容
     * @param no,type
     *@Return:java.lang.Object
     *@Data:21:10 2020/1/30
     **/
    @PostMapping("/modifyLikes")
    public Object ModifyLikes(String no,int type){
        String userId = ShiroUtils.getUserId();
        no = ProjectConstant.CLITERPREFIX + no;

        if (type==1){
                redisCacheService.setHashValue("c_like_lit_"+no,userId,"1");
                System.out.println(redisCacheService.getHashMap("c_like_lit_"+no));
        }else if(type==0){
            Boolean b = redisCacheService.hashFieldExist("c_like_lit_"+no,userId);
            if(b){
                if(redisCacheService.delHashField("c_like_lit_"+no,userId).equals(0)){
                    return AjaxResult.error(CodeEnum.REDIS_EXCEPTION.getCode(),CodeEnum.REDIS_EXCEPTION.getMessage(),null);
                }
            }else {
                literContentService.DelLikeSts(userId,no);
            }
        }
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }

}
