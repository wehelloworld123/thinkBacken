package com.myIsoland.controller.community;

import com.alibaba.fastjson.JSON;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.service.community.CorporationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 社团controller
 * @author xuyong
 * @date: 2019年11月20日 下午4:23:50
 */
@RestController
@Api(value = "社团信息")
@RequestMapping("Corporation")
public class CorporationController {

    @Autowired
    private CorporationService corporationService;

    public CorporationController(){

    }
    @GetMapping("/getCorps")
    public Object GetCorps(){
        try {
            return AjaxResult.success(corporationService.GetCorporation().toString());
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }
}
