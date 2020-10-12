package com.myIsoland.controller.creation;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.component.SingleParamException;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.constant.RedisConstant;
import com.myIsoland.enitity.product.Recommend;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.RecomType;
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
@Api(value = "作品推荐界面")
@RequestMapping("Recomment")
public class RecommentController {
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private RedisCacheService redisCacheService;

    /**
     *@Author:THINKPAD
     *@Description:更新内容点赞喜欢
     * @param id
     *@Return:java.lang.Object
     *@Data:13:42 2020/2/4
     **/
    @PostMapping("/modifyLikesSts")
    public Object ModifyLikesSts(String id,int type){
        String userId = ShiroUtils.getUserId();
        Long no = new Long(id);

        if (type==1){
            redisCacheService.setHashValue(RedisConstant.CRECOM+no,userId,"1");
            System.out.println(redisCacheService.getHashMap(RedisConstant.CRECOM+id));
        }else if(type==0){
            Boolean b = redisCacheService.hashFieldExist(RedisConstant.CPOETRY+no,userId);
            if(b){
                if(redisCacheService.delHashField(RedisConstant.CRECOM+no,userId).equals(0)){
                    return AjaxResult.error(CodeEnum.REDIS_EXCEPTION.getCode(),CodeEnum.REDIS_EXCEPTION.getMessage(),null);
                }
            }else {
                recommendService.DelLikeSts(userId,no);
            }
        }
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
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
    public Object CreateRecommend(String content,Long rootId,int kind,String title,String summary)throws SingleParamException{
        Recommend data = new Recommend();
        data.setContent(content);
        TsysUser tsysUser = ShiroUtils.getUser();
        if(RecomType.LITERATURE.getValue()==kind){
            data.setContentId(ProjectConstant.CLITERPREFIX+rootId);
        }else if(RecomType.PAINTING.getValue()==kind){
            data.setContentId(ProjectConstant.CPAINTINGPREFIX+rootId);
        }else if(RecomType.POEMTY.getValue()==kind){
            data.setContentId(ProjectConstant.CPOETRYPREFIX+rootId);
        }else{
            throw new  SingleParamException("类型查找错误");
        }
        data.setContentId(ProjectConstant.CLITERPREFIX+rootId);
        data.setKind(kind);
        data.setCreator(tsysUser.getUsername());
        data.setCreateAvat(tsysUser.getAvatar());
        data.setTitle(title);
        data.setSummary(summary);
        data.setFavorer(";");
        recommendService.save(data);
        return AjaxResult.success(data);
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
    public Object ReadRecommends(String no,String date,int type,int start,int limit,Boolean isLogin) throws SingleParamException {
        System.out.println("no:"+no);
        if(RecomType.LITERATURE.getValue()==type){
            no = ProjectConstant.CLITERPREFIX + no;
        }else if(RecomType.PAINTING.getValue()==type){
            no = ProjectConstant.CPAINTINGPREFIX + no;
        }else if(RecomType.POEMTY.getValue()==type){
            no = ProjectConstant.CPOETRYPREFIX + no;
        }else{
            throw new  SingleParamException("类型查找错误");
        }
        String userId=null;
        if(isLogin){
            userId=ShiroUtils.getUserId();
        }
        List<Recommend> data = recommendService.GetRecommentByDate
                (userId,no, DateUtils.parseDate(date),RecomType.valueOf(type),start,limit);
        return AjaxResult.success(CaculateUtils.deleteRecomPrefix(data));
    }

}
