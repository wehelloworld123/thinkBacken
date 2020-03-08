package com.myIsoland.controller.personcenter;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.UserProduct;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.RecomType;
import com.myIsoland.service.product.UserProductService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@Api(value = "用户邀请创作接口")
@RequestMapping("UserInvitPro")
public class UserInvitProController {
    @Autowired
    private UserProductService userProductService;

    @GetMapping("/readUserProductions")
    public Object ReadUserProductions(int type,String date,int kind,int page){
        String userId = ShiroUtils.getUserId();
        List<UserProduct> products = new LinkedList<>();
        if(RecomType.valueOf(type).equals(RecomType.LITERATURE)){
            products = userProductService.GetUserLiteratures(userId,kind,DateUtils.parseDate(date),page);
        }else if(RecomType.valueOf(type).equals(RecomType.PAINTING)){
            products =userProductService.GetUserPaintings(userId,kind,DateUtils.parseDate(date),page);
        }else if(RecomType.valueOf(type).equals(RecomType.POEMTY)){
            products =userProductService.GetUserPoemsint(userId,kind,DateUtils.parseDate(date),page);
        }
        return AjaxResult.success(products);
    }


    @GetMapping("/delUserProduction")
    public Object DelUserProduction(int type,Long id){
        String userId = ShiroUtils.getUserId();
        userProductService.DelUserProduct(userId,id);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS);
    }

}
