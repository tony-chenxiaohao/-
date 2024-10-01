package com.ruoyi.web.controller.system;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.RSAUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.controller.domain.dto.SysUserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {
    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private TokenService tokenService;

    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile() {
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUser();
        SysUserProfileDto sysUserProfileDto = BeanUtil.toBean(user, SysUserProfileDto.class);
        AjaxResult ajax = AjaxResult.success(sysUserProfileDto);
        ajax.put("roleGroup", sysUserService.selectUserRoleGroup(loginUser.getUsername()));
        ajax.put("postGroup", sysUserService.selectUserPostGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateProfile(@RequestBody SysUser user) {
        LoginUser loginUser = getLoginUser();
        SysUser currentUser = loginUser.getUser();
        currentUser.setNickName(user.getNickName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhonenumber(user.getPhonenumber());
        currentUser.setSex(user.getSex());
        if (StringUtils.isNotEmpty(user.getPhonenumber()) && !sysUserService.checkPhoneUnique(currentUser)) {
            return error("修改用户'" + loginUser.getUsername() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getEmail()) && !sysUserService.checkEmailUnique(currentUser)) {
            return error("修改用户'" + loginUser.getUsername() + "'失败，邮箱账号已存在");
        }
        if (sysUserService.updateUserProfile(currentUser) > 0) {
            // 更新缓存用户信息
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
//    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
//    @PutMapping("/updatePwd")
//    public AjaxResult updatePwd(String oldPassword, String newPassword)
//    {
//        LoginUser loginUser = getLoginUser();
//        String userName = loginUser.getUsername();
//        String password = loginUser.getPassword();
//        if (!SecurityUtils.matchesPassword(oldPassword, password))
//        {
//            return error("修改密码失败，旧密码错误");
//        }
//        if (SecurityUtils.matchesPassword(newPassword, password))
//        {
//            return error("新密码不能与旧密码相同");
//        }
//        newPassword = SecurityUtils.encryptPassword(newPassword);
//        if (sysUserService.resetUserPwd(userName, newPassword) > 0)
//        {
//            // 更新缓存用户密码
//            loginUser.getUser().setPassword(newPassword);
//            tokenService.setLoginUser(loginUser);
//            return success();
//        }
//        return error("修改密码异常，请联系管理员");
//    }
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updatePwd")
    public AjaxResult updatePwd(@RequestBody HashMap<String, String> map) throws Exception {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String userName = loginUser.getUsername();
        //加密后的
        String password = loginUser.getPassword();
        String oldPassword;
        String newPassword;
        //解密
        oldPassword = map.get("oldPassword");
        newPassword = map.get("newPassword");
//        if (newPassword.equals("123456")) {
//            return AjaxResult.error("此密码不能用，请换个密码！");
//        }
        //拿原密码和加密后的解密
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        if (sysUserService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0) {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }


    /**
     * 修改密码
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updatePwdMp")
    public AjaxResult updatePwdMp(@RequestBody HashMap<String, String> map) throws Exception {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String userName = loginUser.getUsername();
        // 加密后的旧密码
        String password = loginUser.getPassword();
        String newPassword;
        // 解密新密码
        newPassword = RSAUtil.decryptByPrivateKey(map.get("newPassword"));

        if (newPassword.equals("123456")) {
            return AjaxResult.error("此密码不能用，请换个密码！");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        if (sysUserService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0) {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            LoginUser loginUser = getLoginUser();
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file, MimeTypeUtils.IMAGE_EXTENSION);
            if (sysUserService.updateUserAvatar(loginUser.getUsername(), avatar)) {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(avatar);
                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return error("上传图片异常，请联系管理员");
    }
}
