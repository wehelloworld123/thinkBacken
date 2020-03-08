package com.myIsoland.controller.community;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.community.Comment;
import com.myIsoland.enitity.community.Disscuss;
import com.myIsoland.enitity.system.TSysRoleUser;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.SexType;
import com.myIsoland.service.community.CommentService;
import com.myIsoland.service.community.DisscussService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 讨论controller
 * @author xuyong
 * @date: 2019年11月20日 下午4:23:50
 */
@RestController
@Api(value = "帖子信息")
@RequestMapping("Disscuss")
public class DisscussController {
    @Autowired
    private DisscussService disscussService;
    @Autowired
    private RedisCacheService redisCacheService;


    /**
     *@Author:THINKPAD
     *@Description:根据热度获取数据
     * @param likes
     * @param concern
     *@Return:java.lang.Object
     *@Data:23:39 2019/11/24
     **/
    @GetMapping("/readHotLikeDiscuss")
    public Object ReadHotDiscuss(int likes,String date){
        return AjaxResult.success(disscussService.GetHotDisscussByLike(ShiroUtils.getUserId(),likes, DateUtils.parseDate(date),15));
    }
    /**
     *@Author:THINKPAD
     *@Description:根据日期获取帖子信息
     * @param
     *@Return:java.lang.Object
     *@Data:23:17 2019/11/23
     **/
    @GetMapping("/readNewDiscuss")
    public Object ReadNewDiscuss(String date,int page){
        List<Disscuss> result = disscussService.GetNewDateDisscuss(ShiroUtils.getUserId(),DateUtils.parseDate(date),page*14,14);
        return AjaxResult.success(result);
    }

    /**
     *@Author:THINKPAD
     *@Description:根据日期时间读取关注帖子
     * @param date
     *@Return:java.lang.Object
     *@Data:19:48 2019/11/25
     **/
    @GetMapping("/readConcernDisByDate")
    public Object ReadConcernDisByDate(String date,int page){
        return AjaxResult.success(disscussService.GetConcernDis(ShiroUtils.getUserId(),DateUtils.parseDate(date),page*14,14));
    }

    /**
     *@Author:THINKPAD
     *@Description:创建帖子
     * @param
     *@Return:java.lang.Object
     *@Data:20:23 2019/11/25
     **/
    @PostMapping("/createDiscuss")
    public Object CreateDiscuss(String content, @RequestParam(value = "imagefile",required = false) MultipartFile imagefile, String location, String label){
        TsysUser user =  ShiroUtils.getUser();
        Disscuss data = new Disscuss();
        data.setContent(content);
        //data.setPic(pic);
        data.setLocation(location);
        data.setLabel(label);
        data.setCreator(user.getUsername());
        data.setCreatorAvat(user.getAvatar());
        data.setCreatorSex(user.getSex());
        int i = disscussService.SaveDisscuss (data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());

    }

    @GetMapping("/readDiscuss")
    public Object ReadDiscuss(Long id){

        Disscuss disscuss = disscussService.GetDiscussInfo(id,ShiroUtils.getUserId(),null);

        return AjaxResult.success(disscuss);

    }

    /**
     *@Author:THINKPAD
     *@Description:更新喜欢状态
     * @param
     *@Return:java.lang.Object
     *@Data:11:50 2020/2/8
     **/
    @GetMapping("/modifyLikeSts")
    public Object ModifyLikeSts(Long id){
        String userId = ShiroUtils.getUserId();
        redisCacheService.setHashValue("f_like_dis_"+id,userId,"1");
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }

    /**
     *@Author:THINKPAD
     *@Description:删除喜欢状态
     * @param
     *@Return:java.lang.Object
     *@Data:11:50 2020/2/8
     **/
    @GetMapping("/delLikeSts")
    public Object DelLikeSts(Long id){
        String userId = ShiroUtils.getUserId();
        Long re = redisCacheService.delHashField("f_like_dis_"+id,userId);
        if (re.equals(0)){
            disscussService.delLikeSts(userId,id);
        }
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}
