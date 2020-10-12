package com.myIsoland.controller.personcenter;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.UserProduct;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.RecomType;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.UserProductService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "用户邀请创作接口")
@RequestMapping("UserInvitPro")
public class UserInvitProController {
    @Autowired
    private UserProductService userProductService;

    @GetMapping("/readUserProductions")
    public Object ReadUserProductions(int type,String date,int kind,int start,int limit){
        String userId = ShiroUtils.getUserId();
        ResultSet<Map<String,Object>> resultSet = new ResultSet<>();

        if(RecomType.valueOf(type).equals(RecomType.LITERATURE)){
            resultSet.setList(userProductService.GetUserLiteratures(userId,kind,type,DateUtils.parseDate(date),start,limit));
        }else if(RecomType.valueOf(type).equals(RecomType.PAINTING)){
            resultSet.setList(userProductService.GetUserPaintings(userId,kind,type,DateUtils.parseDate(date),start,limit));
        }else if(RecomType.valueOf(type).equals(RecomType.POEMTY)){
            resultSet.setList(userProductService.GetUserPoemsint(userId,kind,type,DateUtils.parseDate(date),start,limit));
        }
        return AjaxResult.success(resultSet);
    }


    @GetMapping("/delUserProduction")
    public Object DelUserProduction(int type,Long id){
        String userId = ShiroUtils.getUserId();
        userProductService.DelUserProduct(userId,id);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS);
    }

}
