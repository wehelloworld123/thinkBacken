package com.myIsoland.controller.creation;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.StringUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.product.LiterCharpt;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enitity.product.UserCreation;
import com.myIsoland.enitity.product.UserProduct;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.enums.ProStatus;
import com.myIsoland.enums.RecomType;
import com.myIsoland.service.product.*;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@Api(value = "文学展示界面")
@RequestMapping("Literature")
public class LiteratureController {
    @Autowired
    private LiteratureService literatureService;
    @Autowired
    private UserCreationService userCreationService;
    @Autowired
    private UserProductService userProductService;
    @Autowired
    private LiterCharpService literCharpService;
    @Autowired
    private RedisCacheService redisCacheService;
    /**
     *@Author:THINKPAD
     *@Description:获取文学创作信息
     * @param type
     * @param partner
     * @param views
     *@Return:java.lang.Object
     *@Data:19:47 2020/1/29
     **/
    @GetMapping("/readLiteratures")
    public Object ReadLiteratures(int type,int partner,int views){
       Map<String,Object> map = new HashMap<>();
        List<Literature> literatures;
       if(partner>=999){
           literatures = literatureService.GetInitLiterByType(CreateKind.valueOf(type),partner,views);
       }else {
           literatures = literatureService.GetLiteratureByType(CreateKind.valueOf(type),partner,views);
       }
       List<Map<String,Object>> advanceLiters = literatureService.GetAdvanceLiterature();
       map.put("literatures", CaculateUtils.deleteLiteraturePrefix(literatures));
       map.put("advanceLiters",advanceLiters);
       return AjaxResult.success(map);

    }


    /**
     *@Author:THINKPAD
     *@Description: 下拉文学创作信息
     * @param type
     * @param partner
     * @param views
     *@Return:java.lang.Object
     *@Data:19:47 2020/1/29
     **/
    @GetMapping("/readNextLiteratures")
    public Object ReadNextLiteratures(int type,int partner,int views){
        System.out.println("type:"+type);
        System.out.println("partner:"+partner);
        System.out.println("views:"+views);
        Map<String,Object> map = new HashMap<>();
        List<Literature> literatures = literatureService.GetLiteratureByType(CreateKind.valueOf(type),partner,views);
        map.put("literatures",CaculateUtils.deleteLiteraturePrefix(literatures));
        return AjaxResult.success(map);
    }


    @GetMapping("/readAdvanceLiters")
    public Object ReadAdvanceLiters(){
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> advanceLiters = literatureService.GetAdvanceLiterature();
        map.put("advanceLiters",advanceLiters);
        return AjaxResult.success(advanceLiters);
    }

    /**
     *@Author:THINKPAD
     *@Description:根据书籍Id获取书籍详细信息以及参与人员
     * @param id
     *@Return:java.lang.Object
     *@Data:21:33 2020/1/29
     **/
    @GetMapping("/readLiteratureDetail")
    public Object ReadLiteratureDetail(String id,int type){
        Map<String,Object> data = new HashMap<>();
        id = ProjectConstant.LITERPREFIX + id;
        if(type==1||type==0) {
            Literature literature = literatureService.GetLiteratureById(id);
            data.put("literature",CaculateUtils.deletePrefix(literature));
        }
        if(type==0||type==2) {
            List<Map<String, Object>> partner = userCreationService.GetCreatPartInfo(id, RecomType.LITERATURE);
            data.put("partner",partner);
        }
        redisCacheService.zsetByScore("liter_views",id,1);
        return AjaxResult.success(data);

    }
    /**
     *@Author:THINKPAD
     *@Description:获取书籍章节信息
     * @param id
     *@Return:java.lang.Object
     *@Data:22:18 2020/1/29
     **/
    @GetMapping("/readLiteratureChaps")
    public Object ReadLiteratureCharps(String id){
        id = ProjectConstant.LITERPREFIX + id;
        System.out.println("id:"+id);
        List<LiterCharpt>  charpts= literCharpService.GetBookCharpt(id);
        return AjaxResult.success(CaculateUtils.deleteChartpsPrefix(charpts));
    }

    @PostMapping("/createUserLiter")
    public Object CreateUserStoreLiter(String id,int type,int kind){
        UserProduct data = new UserProduct();
        if(RecomType.LITERATURE.getValue()==type){
            id = ProjectConstant.CLITERPREFIX + id;
        }
        data.setCreationId(id);
        data.setType(type);
        if(ProStatus.store.getValue()==kind){
            data.setKind(kind);
            data.setStatus(1);
        }
        data.setUserId(ShiroUtils.getUserId());
        userProductService.saveOrUpdate(data);
        return  AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}
