package com.myIsoland.controller.personcenter;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.product.Recommend;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.RecomType;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.RecommendService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户推荐界面")
@RequestMapping("UserRecommend")
public class UserRecommend {
    @Autowired
    private RecommendService recommendService;


    @GetMapping("/readUserRecommend")
    public Object ReadUserRecommend(int type,String date,int start,int limit){
        String userId = ShiroUtils.getUserId();
        ResultSet<Recommend> resultSet = recommendService.GetUserRecommentByDate(userId, DateUtils.parseDate(date), RecomType.valueOf(type),start,limit);

        return AjaxResult.success(resultSet);
    }


    @GetMapping("/delUserRecommend")
    public Object DelUserRecommend(Long id){
        recommendService.removeById(id);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}
