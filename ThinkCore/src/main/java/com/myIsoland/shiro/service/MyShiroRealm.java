package com.myIsoland.shiro.service;


import com.myIsoland.common.base.ThinkException;
import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.util.StringUtils;
import com.myIsoland.enitity.system.TsysPermission;
import com.myIsoland.enitity.system.TsysRole;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.service.system.TsysPermissionService;
import com.myIsoland.service.system.TsysRoleService;
import com.myIsoland.service.system.TsysUserService;
import com.myIsoland.shiro.config.UserPhoneToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 身份校验核心类
 * 
 * @ClassName: MyShiroRealm
 * @author fuce
 * @date 2018年8月25日
 *
 */
@SuppressWarnings("ALL")
@Service
public class MyShiroRealm extends AuthorizingRealm {
	
	
	
	@Autowired
	private TsysUserService tsysUserService;
	@Autowired
	private TsysPermissionService tsysPermissionService;//权限dao
	@Autowired
	private TsysRoleService tsysRoleService;//角色dao
	@Autowired
	private RedisCacheService redisCacheService;



	/**
	 * 认证登陆
	 */
	@SuppressWarnings("unused")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if(token instanceof UserPhoneToken) {
			//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
			if (token.getPrincipal() == null) {
				return null;
			}
			String username = (String) token.getPrincipal();
			String verifyCode = new String((char[]) token.getCredentials());
			// 通过username从数据库中查找 User对象，如果找到，没找到.
			// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法

			TsysUser userInfo = tsysUserService.getUserByName(username);

//		System.out.println(userInfo);
//		System.out.println("----->>userInfo=" + userInfo.getUsername() + "---"+ userInfo.getPassword());
			if (userInfo == null) {
				throw new ThinkException(CodeEnum.LOGIN_FAIL_CODE);
			}
			else {
				//用户不为空则是缓存中取验证码，获取验证码后我将验证码存入了ehcache缓存，过期时间3分钟


				SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
						userInfo, // 用户
						username, // 密码
						getName() // realm name
				);
				redisCacheService.delete(username+"_code");
				return authenticationInfo;
			}
		}else{
			//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
			if (token.getPrincipal() == null) {
				return null;
			}
			String username = (String) token.getPrincipal();
			String password = new String((char[]) token.getCredentials());
			// 通过username从数据库中查找 User对象，如果找到，没找到.
			// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法

			TsysUser userInfo = tsysUserService.getUserByName(username);

//		System.out.println(userInfo);
//		System.out.println("----->>userInfo=" + userInfo.getUsername() + "---"+ userInfo.getPassword());
			if (userInfo == null)
				return null;
			else {

				SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
						userInfo, // 用户名
						userInfo.getPassword(), // 密码
						getName() // realm name
				);
				return authenticationInfo;
			}
		}
		
	}
	
	 /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		if(principals == null){  
	       throw new AuthorizationException("principals should not be null");  
	    }
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		TsysUser userinfo  = (TsysUser)principals.getPrimaryPrincipal();
		String uid=userinfo.getId();
		
		List<TsysRole> tsysRoles= tsysRoleService.getRolesById(uid);
		
		
		for(TsysRole userrole:tsysRoles){
			//System.out.println("角色名字:"+gson.toJson(userrole));
			String rolid=userrole.getId();//角色id
			authorizationInfo.addRole(userrole.getName());//添加角色名字
			List<TsysPermission> premissions=tsysPermissionService.getPermissionByRoleid(rolid);
			for(TsysPermission p:premissions){
				//System.out.println("角色下面的权限:"+gson.toJson(p));
				if(StringUtils.isNotEmpty(p.getPerms())){
					authorizationInfo.addStringPermission(p.getPerms());
				}
				
			}
		}
 
 
		
		return authorizationInfo;
	}
	//Shiro令牌
	public boolean supports(AuthenticationToken authenticationToken) {
		//判断A 是否是B的父类
		if(authenticationToken instanceof  UsernamePasswordToken){
			return true;
		}
		return true;
	}
	 /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo()
    {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

}
