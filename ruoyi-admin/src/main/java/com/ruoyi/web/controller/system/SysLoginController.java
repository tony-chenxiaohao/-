package com.ruoyi.web.controller.system;

import cn.hutool.core.bean.BeanUtil;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginBodyPhone;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.RSAUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.security.context.AuthenticationContextHolder;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import com.ruoyi.web.controller.domain.dto.SysUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
@Slf4j
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private SysUserServiceImpl sysUserService;
    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }



    /**
     * 获取公钥：前端用来密码加密
     *
     * @return
     */
    @GetMapping("/getPublicKey")
    public RSAUtil.RSAKeyPair getPublicKey() {
        return RSAUtil.rsaKeyPair();
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        SysUserDTO userDTO = BeanUtil.toBean(user, SysUserDTO.class);
        userDTO.setDeptName(user.getDept().getDeptName());

        // 获取角色集合
        Set<String> roles = permissionService.getRolePermission(user);

        // 获取权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);

        // 初始化返回对象
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", userDTO);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);

        // 判断用户是否第一次登录，并检查密码是否为默认密码
//        String defaultPasswordEncrypted = SecurityUtils.encryptPassword("123456");  // 加密后的默认密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        SysUserDTO sysUser = sysUserService.selectJoinOne(
                SysUserDTO.class,
                new MPJLambdaWrapper<SysUser>().eq(SysUser::getUserName, SecurityUtils.getLoginUser().getUser().getUserName())
        );

        if (sysUser != null) {
            String storedPassword = sysUser.getPassword();  // 数据库中的密码

            // 判断数据库中的密码是否为加密后的默认密码
            if (passwordEncoder.matches("123456", storedPassword)) {
                ajax.put("isFirstLogin", true);
            } else {
                ajax.put("isFirstLogin", false);
            }
        }

        return ajax;
    }


    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
