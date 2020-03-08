package com.myIsoland.controller.think;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.file.FileUploadUtils;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enitity.think.Theory;
import com.myIsoland.service.think.TheoryService;
import com.myIsoland.shiro.util.ShiroUtils;
import com.myIsoland.util.SnowflakeIdWorker;
import com.myIsoland.util.StringUtils;
import io.swagger.annotations.Api;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Api(value = "理论界面")
@RequestMapping("Theory")
public class TheoryController {
    @Autowired
    private TheoryService theoryService;

    /**
     *@Author:THINKPAD
     *@Description:读取用户理论标题信息
     * @param
     *@Return:java.lang.Object
     *@Data:14:28 2020/1/4
     **/
    @GetMapping("/readTheoryTitle")
    public Object ReadTheoryTitle(){
        TsysUser user = ShiroUtils.getUser();
        System.out.println(ShiroUtils.getUserId());
        try {
            return AjaxResult.success(theoryService.GetUserTheory(user.getId()));
        }catch (Exception e){
            throw e;
            //return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }

  /*  @RequestMapping("/test")
    public Object test(HttpServletRequest request, RedirectAttributes redirectAttributes){
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile imagefile =  req.getFile("imagefile");
        String title = request.getParameter("title");
        String describe = request.getParameter("describe");
        try {
            String cover = null;
            if(imagefile!=null) {
                cover = FileUploadUtils.upload(imagefile);
            }
            Theory data = new Theory(SnowflakeIdWorker.getUUID(),title,describe,cover);

            int i =theoryService.UpdateTheory(data);
            if(THINKConstant.SQL_SUCCESS.equals(i)){
                return AjaxResult.success(THINKConstant.SQL_SUCCESS_MSG);
            }else{
                return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,THINKConstant.SQL_FAIL_MSG);
            }
        }catch (IOException e) {
            return AjaxResult.error(THINKConstant.SAVE_FILE_FAIL_CODE,e.getMessage());
        }catch (Exception e){
            return AjaxResult.error(THINKConstant.SQL_EXCEPTION_CODE,e.getMessage());
        }
    }*/
    /**
     *@Author:THINKPAD
     *@Description:修改理论信息
     * @param title
     * @param describe
     * @param imagefile
     *@Return:java.lang.Object
     *@Data:14:29 2020/1/4
     **/
    @PostMapping("/modifyTheory")
    public Object ModifyTheory(String title, String describe, @RequestParam(value = "imagefile",required = false)
            MultipartFile  imagefile) throws IOException {
            String cover = null;
            if(imagefile!=null) {
                cover = FileUploadUtils.upload(imagefile);
            }
            Theory data = new Theory(SnowflakeIdWorker.getUUID(),title,describe,cover);

            theoryService.UpdateTheory(data);
            return AjaxResult.success(THINKConstant.SQL_SUCCESS_MSG);

    }

    /**
     *@Author:THINKPAD
     *@Description:创建理论信息
     * @param title
     * @param describe
     * @param imagefile
     *@Return:java.lang.Object
     *@Data:14:29 2020/1/4
     **/
    @PostMapping("/createTheory")
    public Object CreateTheory(String title, String describe, @RequestParam(value = "imagefile",required = false) MultipartFile  imagefile)throws IOException{

           String cover = FileUploadUtils.upload(imagefile);

           Theory data = new Theory(SnowflakeIdWorker.getUUID(),title,describe,cover);

           theoryService.SaveTheory(data);

            return AjaxResult.success(THINKConstant.SQL_SUCCESS_MSG);

    }
}
