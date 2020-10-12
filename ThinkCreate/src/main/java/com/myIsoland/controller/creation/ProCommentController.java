package com.myIsoland.controller.creation;

import com.myIsoland.common.component.SingleParamException;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.ProComment;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.RecomType;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.ProCommentService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "作品评价接口")
@RequestMapping("ProComment")
public class ProCommentController {
    private final Logger logger = LoggerFactory.getLogger(ProCommentController.class);
    @Autowired
    private ProCommentService proCommentService;

    /**
     *@Author:THINKPAD
     *@Description:读取评论
     * @param uid
     * @param date
     * @param type
     * @param start
     *@Return:java.lang.Object
     *@Data:0:02 2020/1/31
     **/
    @GetMapping("/readProRecommends")
    public Object ReadProRecommends(String uid,String date,int type,int start,int limit) throws SingleParamException {
        if (RecomType.LITERATURE==RecomType.valueOf(type)){
            uid = ProjectConstant.LITERPREFIX+uid;
        }else if(RecomType.PAINTING==RecomType.valueOf(type)){
            uid = ProjectConstant.PAINTINGPREFIX+uid;
        }else if(RecomType.POEMTY==RecomType.valueOf(type)){
            uid = ProjectConstant.POETRYPREFIX + uid;
        } else{
            throw new  SingleParamException("类型查找错误");
        }

        ResultSet<ProComment> data = proCommentService.GetProCommentsByDate(uid,RecomType.valueOf(type),DateUtils.parseDate(date),start,limit);
        return AjaxResult.success(data);
    }
    /**
     *@Author:THINKPAD
     *@Description:保存作品的用户评价
     * @param uid,type,content,rate
     *@Return:java.lang.Object
     *@Data:13:42 2020/2/4
     **/
    @PostMapping("/saveProComment")
    public Object SaveProComment(String uid,int type,String content,int rate) throws SingleParamException {
        TsysUser userInfo = ShiroUtils.getUser();

        if (RecomType.LITERATURE==RecomType.valueOf(type)){
            uid = ProjectConstant.LITERPREFIX+uid;
        }else if(RecomType.PAINTING==RecomType.valueOf(type)){
            uid = ProjectConstant.PAINTINGPREFIX+uid;
        }else if(RecomType.POEMTY==RecomType.valueOf(type)){
            uid = ProjectConstant.POETRYPREFIX + uid;
        }else{
            throw new SingleParamException("类型查找错误");
        }
        ProComment data = new ProComment();
        data.setCreationId(uid);
        data.setTyp(type);
        data.setContent(content);
        data.setRate(rate);
        data.setCreator(userInfo.getNickname());
        data.setCreatorAvat(userInfo.getAvatar());
        proCommentService.saveOrUpdate(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}
