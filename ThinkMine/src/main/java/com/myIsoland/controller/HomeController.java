package com.myIsoland.controller;

import com.alibaba.fastjson.JSONObject;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.StringUtils;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value = "登陆界面")
@RequestMapping("Home")
public class HomeController {
    private static Logger logger= LoggerFactory.getLogger(HomeController.class);
    @PostMapping("login")
    public Object login(HttpServletRequest request,RedirectAttributes redirectAttributes) {

        String info = request.getParameter("TsysUser");
        TsysUser user = JSONObject.parseObject(info,TsysUser.class);
        String rememberMe = request.getParameter("remember");
        String userName = user.getUsername();
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()) {
            UsernamePasswordToken token =new UsernamePasswordToken(userName,user.getPassword());
            try {
                if(!StringUtils.equals(rememberMe,"deleteMe")) {
                    token.setRememberMe(true);
                }
                //存入用户
                currentUser.login(token);
            }catch (UnknownAccountException uae) {
                logger.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
                redirectAttributes.addFlashAttribute("message", "未知账户");
            } catch (IncorrectCredentialsException ice) {
                logger.info("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
                redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
            } catch (LockedAccountException lae) {
                logger.info("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
                redirectAttributes.addFlashAttribute("message", "账户已锁定");
            } catch (ExcessiveAttemptsException eae) {
                logger.info("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
                redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
            } catch (AuthenticationException ae) {
                //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
                logger.info("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
                ae.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
            }
        }


        if(StringUtils.isNotNull(ShiroUtils.getUser())) {
            //跳转到 get请求的登陆方法
            return AjaxResult.success();
        }


        return AjaxResult.error(CodeEnum.LOGIN_FAIL_CODE.getCode(),CodeEnum.LOGIN_FAIL_CODE.getMessage(),null);

    }
}
