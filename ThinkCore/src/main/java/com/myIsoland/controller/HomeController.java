package com.myIsoland.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.code.kaptcha.Constants;
import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.config.V2Config;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.*;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.service.system.TsysUserService;
import com.myIsoland.shiro.config.UserPhoneToken;
import com.myIsoland.shiro.util.ShiroUtils;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Api(value = "登陆界面")
@RequestMapping("Home")
public class HomeController {
    private static Logger logger= LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private TsysUserService sysUserService;

    @PostMapping("preLogin")
    public Object PreLogin(String username) throws Exception {
        KeyPair keyPair = RSAUtil.getKeyPair();

        String privateKeyStr = RSAUtil.getPrivateKey(keyPair);//获取私钥字符串

        String publicKeyStr = RSAUtil.getPublicKey(keyPair);//获取公钥字符串

        redisCacheService.set(username,privateKeyStr,new Long(60), TimeUnit.MINUTES);//保存私钥到redis

        return  AjaxResult.success(publicKeyStr);//返回公钥字符串

    }

    @PostMapping("codeLogin")
    public Object CodeLogin(HttpServletRequest request,RedirectAttributes redirectAttributes,String username,String code,boolean verify) throws Exception {
        if(!verify) {
            if (RegexUtils.checkMobile(username)) {
                Map<String, Object> map = SmsUtil.sendSms(username);
                if (map != null&&(Boolean) map.get("status")) {
                    map.put("ip", CusAccessObjectUtil.getIpAddress(request));
                    redisCacheService.set(username+"_login", map, new Long(3), TimeUnit.MINUTES);//保存验证码到redis
                    return AjaxResult.success(map);
                }else {
                    return AjaxResult.error("发送验证码失败");
                }
            }else {
                return AjaxResult.error("参数校验出错");
            }
        }else{
            String rememberMe = request.getParameter("remember");

            Map<String,Object> map = (Map<String,Object>) redisCacheService.get(username+"_login");

            String ip = CusAccessObjectUtil.getIpAddress(request);
            if(map!=null&&ip.equals((String)map.get("ip"))){
                if(!StringUtils.isEmpty(code)&&code.equals((String)map.get("verifyCode"))) {
                    //主体,当前状态为没有认证的状态“未认证”
                    Subject currentUser = SecurityUtils.getSubject();
                    if (!currentUser.isAuthenticated()) {
                        //创建令牌，将手机号和验证码丢进去
                        UserPhoneToken token = new UserPhoneToken(username);
                        TsysUser user;
                        try {
                            //登录
                            currentUser.login(token);
                            if(!StringUtils.equals(rememberMe,"deleteMe")) {
                                token.setRememberMe(true);
                            }
                            //登录成功，则认证成功，rememberMe和这个认证的状态是互斥的

/*                                //获得session
                                Session subjectSession = subject.getSession();
                                //去数据里查找当前用户
                                user = icUserUserService.findUserUserByMobile(mobile);
                                //将用户存于session中
                                subjectSession.setAttribute(CustomActConstans.userSession, user);

                            activityVO.setCode(1);
                            activityVO.setMsg("登录成功!");*/

                        } catch (UnknownAccountException uae) {
                            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
                            redirectAttributes.addFlashAttribute("message", "未知账户");
                        } catch (IncorrectCredentialsException ice) {
                            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
                            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
                        } catch (LockedAccountException lae) {
                            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
                            redirectAttributes.addFlashAttribute("message", "账户已锁定");
                        } catch (ExcessiveAttemptsException eae) {
                            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
                            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
                        } catch (AuthenticationException ae) {
                            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
                            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
                            ae.printStackTrace();
                            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
                        }
                    }
                }else{
                    return AjaxResult.error(THINKConstant.LOGIN_FAIL_CODE,THINKConstant.LOGIN_FAIL_MSG,"登陆地址或者验证码错误");
                }
            }
            if(StringUtils.isNotNull(ShiroUtils.getUser())) {
                //跳转到 get请求的登陆方法
                return AjaxResult.success();
            }


            return AjaxResult.error(THINKConstant.LOGIN_FAIL_CODE,THINKConstant.LOGIN_FAIL_MSG,null);
        }
    }

    /**
     * 重置账号
     * @param request
     * @return
     */
    @PostMapping("/reset")
    public Object reset(String code,String imageCode,String username,RedirectAttributes redirectAttributes,HttpServletRequest request,boolean verify) throws Exception {

        try {

            if(!verify) {//重置发送手机验证码
                String scode = (String)redisCacheService.get(request.getRemoteHost()+"");
                //判断验证码
                if(StringUtils.isNotEmpty(scode)&&StringUtils.isNotEmpty(imageCode)&&scode.equals(imageCode)) {
                    redisCacheService.delete(request.getRemoteHost()+"");
                    if (RegexUtils.checkMobile(username)) {
                        KeyPair keyPair = RSAUtil.getKeyPair();

                        String privateKeyStr = RSAUtil.getPrivateKey(keyPair);//获取私钥字符串

                        String publicKeyStr = RSAUtil.getPublicKey(keyPair);//获取公钥字符串
                        redisCacheService.set(username + "_key", privateKeyStr, new Long(60), TimeUnit.MINUTES);//保存私钥到redis

                        Map<String, Object> data = new HashMap<>();
                        data.put("publicKey", publicKeyStr);
                        data.put("privateKey", privateKeyStr);

                        Map<String, Object> map = SmsUtil.sendSms(username);
                        if (map != null && (Boolean) map.get("status")) {
                            map.put("ip", CusAccessObjectUtil.getIpAddress(request));
                            redisCacheService.set(username + "_reset", map, new Long(3), TimeUnit.MINUTES);//保存手机验证码到redis
                            data.put("map", map);
                            return AjaxResult.success(data);
                        } else {
                            return AjaxResult.error("发送验证码失败");
                        }
                    } else {
                        return AjaxResult.error("参数校验出错");
                    }
                }else{
                    redirectAttributes.addFlashAttribute("message", "验证码不正确");
                    return AjaxResult.error(500,"图像验证码有误",null);
                }
            }else {//重置密码
                Map<String,Object> map = (Map<String,Object>) redisCacheService.get(username+"_reset");

                String ip = CusAccessObjectUtil.getIpAddress(request);
                if(map!=null&&ip.equals((String)map.get("ip"))){
                    if(!StringUtils.isEmpty(code)&&code.equals((String)map.get("verifyCode"))) {
                        /**
                         * Step 1 获取请求参数
                         */
                        String info = request.getParameter("TsysUser");
                        TsysUser user = JSONObject.parseObject(info, TsysUser.class);
                        String userName = user.getUsername();

                        /**
                         * Step 2 私钥解密密码
                         */
                        String privateKeyStr = (String) redisCacheService.get(userName + "_key");

                        byte[] content = RSAUtil.base642Byte(user.getPassword());

                        PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);

                        byte[] passwordByte = RSAUtil.decryptByPrivateKey(content, privateKey);

                        String password = new String(passwordByte);
                        if (!StringUtil.isNullOrEmpty(password)) {
                            user.setPassword(MD5Util.encode(password));
                        }
                        UpdateWrapper<TsysUser> wrapper = new UpdateWrapper<>();

                        wrapper.lambda()
                                .eq(TsysUser::getUsername,user.getUsername())
                                .eq(TsysUser::getIsDel,0)
                                .set(TsysUser::getPassword,user.getPassword());
                        boolean b = sysUserService.update(user, wrapper);
                        if (b) {
                            logger.info("success");
                            return AjaxResult.success();
                        } else {
                            logger.info("error");
                            return AjaxResult.error(500, "重置失败",null);
                        }
                    }else{
                        return AjaxResult.error(THINKConstant.LOGIN_FAIL_CODE,THINKConstant.LOGIN_FAIL_MSG,"登陆地址或者验证码错误");
                    }
                }else{
                    return AjaxResult.error(500, "登陆ip异常",null);
                }


            }

        } catch (Exception e) {
            throw e;
        }

    }

    @PostMapping("login")
    public Object login(HttpServletRequest request,RedirectAttributes redirectAttributes,boolean login,String code) throws Exception {
            try {

            if (!login) {//如果不是登陆，而是验证，返回公钥字符串
                String scode = (String)redisCacheService.get(request.getRemoteHost()+"");
                //判断验证码
                if(StringUtils.isNotEmpty(scode)&&StringUtils.isNotEmpty(code)&&scode.equals(code)) {
                    redisCacheService.delete(request.getRemoteHost()+"");
                    String username = (String) request.getParameter("username");
                    KeyPair keyPair = RSAUtil.getKeyPair();

                    String privateKeyStr = RSAUtil.getPrivateKey(keyPair);//获取私钥字符串

                    String publicKeyStr = RSAUtil.getPublicKey(keyPair);//获取公钥字符串
                    redisCacheService.delete(username + "_key");
                    redisCacheService.set(username + "_key", privateKeyStr, new Long(60), TimeUnit.MINUTES);//保存私钥到redis

                    Map<String, Object> data = new HashMap<>();
                    data.put("publicKey", publicKeyStr);
                    data.put("privateKey", privateKeyStr);
                    return AjaxResult.success(data);//返回公钥字符串
                }else {
                    redirectAttributes.addFlashAttribute("message", "验证码不正确");
                    return AjaxResult.error(500,"验证码有误",null);
                }
            }


                /**
                 * Step 1 获取请求参数
                 */
                String info = request.getParameter("TsysUser");
                TsysUser user = JSONObject.parseObject(info, TsysUser.class);
                String rememberMe = request.getParameter("remember");
                String userName = user.getUsername();

                /**
                 * Step 2 私钥解密密码
                 */
                String privateKeyStr = (String) redisCacheService.get(userName + "_key");

                byte[] content = RSAUtil.base642Byte(user.getPassword());

                PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);

                byte[] passwordByte = RSAUtil.decryptByPrivateKey(content, privateKey);

                String password = new String(passwordByte);
                if (!StringUtil.isNullOrEmpty(password)) {
                    user.setPassword(password);
                }
                Subject currentUser = SecurityUtils.getSubject();
                if (!currentUser.isAuthenticated()) {
                    UsernamePasswordToken token = new UsernamePasswordToken(userName, user.getPassword());
                    try {
                        if (!StringUtils.equals(rememberMe, "deleteMe")) {
                            token.setRememberMe(true);
                        }
                        //存入用户
                        currentUser.login(token);
                    } catch (UnknownAccountException uae) {
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


                if (StringUtils.isNotNull(ShiroUtils.getUser())) {
                    //跳转到 get请求的登陆方法
                    return AjaxResult.success();
                }


                return AjaxResult.error(THINKConstant.LOGIN_FAIL_CODE, THINKConstant.LOGIN_FAIL_MSG, null);

            }catch (Exception e) {
            throw e;
        }

    }

    /**
     * 注册账号
     * @param request
     * @return
     */
    @PostMapping("enroll")
    public Object enroll(String code,String imageCode,String username,RedirectAttributes redirectAttributes,HttpServletRequest request,boolean verify) throws Exception {

        try {

            if(!verify) {//重置发送手机验证码
                String scode = (String)redisCacheService.get(request.getRemoteHost()+"");
                //判断验证码
                if(StringUtils.isNotEmpty(scode)&&StringUtils.isNotEmpty(imageCode)&&scode.equals(imageCode)) {
                    redisCacheService.delete(request.getRemoteHost()+"");
                    if (RegexUtils.checkMobile(username)) {
                        KeyPair keyPair = RSAUtil.getKeyPair();

                        String privateKeyStr = RSAUtil.getPrivateKey(keyPair);//获取私钥字符串

                        String publicKeyStr = RSAUtil.getPublicKey(keyPair);//获取公钥字符串
                        redisCacheService.set(username + "_key", privateKeyStr, new Long(60), TimeUnit.MINUTES);//保存私钥到redis

                        Map<String, Object> data = new HashMap<>();
                        data.put("publicKey", publicKeyStr);
                        data.put("privateKey", privateKeyStr);

                        Map<String, Object> map = SmsUtil.sendSms(username);
                        if (map != null && (Boolean) map.get("status")) {
                            map.put("ip", CusAccessObjectUtil.getIpAddress(request));
                            redisCacheService.set(username + "_enroll", map, new Long(3), TimeUnit.MINUTES);//保存手机验证码到redis
                            data.put("map", map);
                            return AjaxResult.success(data);
                        } else {
                            return AjaxResult.error("发送验证码失败");
                        }
                    } else {
                        return AjaxResult.error("参数校验出错");
                    }
                }else{
                    redirectAttributes.addFlashAttribute("message", "验证码不正确");
                    return AjaxResult.error(500,"图像验证码有误",null);
                }
            }else {//新建账号
                Map<String,Object> map = (Map<String,Object>) redisCacheService.get(username+"_enroll");

                String ip = CusAccessObjectUtil.getIpAddress(request);
                if(map!=null&&ip.equals((String)map.get("ip"))){
                    if(!StringUtils.isEmpty(code)&&code.equals((String)map.get("verifyCode"))) {
                        /**
                         * Step 1 获取请求参数
                         */
                        String info = request.getParameter("TsysUser");
                        TsysUser user = JSONObject.parseObject(info, TsysUser.class);
                        String userName = user.getUsername();

                        /**
                         * Step 2 私钥解密密码
                         */
                        String privateKeyStr = (String) redisCacheService.get(userName + "_key");

                        byte[] content = RSAUtil.base642Byte(user.getPassword());

                        PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);

                        byte[] passwordByte = RSAUtil.decryptByPrivateKey(content, privateKey);

                        String password = new String(passwordByte);
                        if (!StringUtil.isNullOrEmpty(password)) {
                            user.setPassword(MD5Util.encode(password));
                        }

                        boolean b = sysUserService.save(user);
                        if (b) {
                            logger.info("success");
                            return AjaxResult.success();
                        } else {
                            logger.info("error");
                            return AjaxResult.error(500, "注册失败",null);
                        }
                    }else{
                        return AjaxResult.error(THINKConstant.LOGIN_FAIL_CODE,THINKConstant.LOGIN_FAIL_MSG,"登陆地址或者验证码错误");
                    }
                }else{
                    return AjaxResult.error(500, "登陆ip异常",null);
                }


            }

        } catch (Exception e) {
            throw e;
        }

    }
}
