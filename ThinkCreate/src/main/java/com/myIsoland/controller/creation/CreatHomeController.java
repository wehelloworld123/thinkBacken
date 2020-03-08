package com.myIsoland.controller.creation;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.service.product.LiteratureService;
import com.myIsoland.service.product.PaintingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "创作首页界面接口")
@RequestMapping("CreatHome")
public class CreatHomeController {
    @Autowired
    private LiteratureService literatureService;

    @GetMapping("/readInitCreatHome")
    public Object ReadInitCreatHome(){
        List<Literature> data = literatureService.GetHotIpLiteratures(40,1000);
        return AjaxResult.success(data);
    }

}
