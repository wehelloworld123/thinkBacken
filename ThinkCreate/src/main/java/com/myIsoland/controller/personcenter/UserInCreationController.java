package com.myIsoland.controller.personcenter;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.LiterContent;
import com.myIsoland.enitity.product.PaintContent;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.RecomType;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.LiterContentService;
import com.myIsoland.service.product.PaintContentService;
import com.myIsoland.service.product.PoemContentService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "用户参与创作页面接口")
@RequestMapping("UserInCreation")
public class UserInCreationController {

    @Autowired
    private LiterContentService literContentService;
    @Autowired
    private PoemContentService poemContentService;
    @Autowired
    private PaintContentService paintContentService;

    @GetMapping("/readUserCreations")
    public Object ReadUserCreations(int type,String date,int start,int limit){
        String userId = ShiroUtils.getUserId();
        if(RecomType.valueOf(type).equals(RecomType.LITERATURE)){
           // LiterContent creation = literContentService.GetUserAdvanceLiterContent(userId,100,300);
            ResultSet<LiterContent> resultSet = literContentService.GetUserLiterContentByDate(userId, DateUtils.parseDate(date),start,limit);
           // data.put("creation",creation);
            return AjaxResult.success(resultSet);
        }else if(RecomType.valueOf(type).equals(RecomType.PAINTING)){
         //   PaintContent creation = paintContentService.GetUserAdvancePaintContent(userId,100,300);
            ResultSet<PaintContent> resultSet = paintContentService.GetUserPaintContentByDate(userId, DateUtils.parseDate(date),start,limit);
         //   data.put("creation",creation);
            return AjaxResult.success(resultSet);
        }else  if(RecomType.valueOf(type).equals(RecomType.POEMTY)){
           // PoemContent creation = poemContentService.GetUserAdvancePoemContent(userId,100,300);
            ResultSet<PoemContent> resultSet = poemContentService.GetUserPoemContentByDate(userId, DateUtils.parseDate(date),start,limit);
          //  data.put("creation",creation);
            return AjaxResult.success(resultSet);
        }else {
            return AjaxResult.error(CodeEnum.INCORRECT_REQ_PARAM.getCode(),CodeEnum.INCORRECT_REQ_PARAM.getMessage(),null);
        }
    }

/*    @GetMapping("/readNextUserCreations")
    public Object ReadNextUserCreations(int type,String date,int page,int start,int limit){
        Map<String,Object> data = new HashMap<>();
        String userId = ShiroUtils.getUserId();
        if(RecomType.valueOf(type).equals(RecomType.LITERATURE)){
            List<LiterContent> creations = literContentService.GetUserLiterContentByDate(userId, DateUtils.parseDate(date),page);
            data.put("creations",creations);
        }else if(RecomType.valueOf(type).equals(RecomType.PAINTING)){
            List<PaintContent> creations = paintContentService.GetUserPaintContentByDate(userId, DateUtils.parseDate(date),page);
            data.put("creations",creations);
        }else  if(RecomType.valueOf(type).equals(RecomType.POEMTY)){
            List<PoemContent> creations = poemContentService.GetUserPoemContentByDate(userId, DateUtils.parseDate(date),page);
            data.put("creations",creations);
        }
        return AjaxResult.success(data);
    }*/

    @GetMapping("/delUserCreations")
    public Object DelUserCreations(int type,String no){
        String userId = ShiroUtils.getUserId();
        if(RecomType.valueOf(type).equals(RecomType.LITERATURE)){
            no = ProjectConstant.CLITERPREFIX + no;
            literContentService.DeleteUserLiterContent(userId,no);

        }else if(RecomType.valueOf(type).equals(RecomType.PAINTING)){
            no = ProjectConstant.CPAINTINGPREFIX + no;
            paintContentService.DelUserPaintContent(userId,no);
        }else  if(RecomType.valueOf(type).equals(RecomType.POEMTY)){
            no = ProjectConstant.CPOETRYPREFIX + no;
            poemContentService.DelUserPoemContent(userId,no);
        }
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}
