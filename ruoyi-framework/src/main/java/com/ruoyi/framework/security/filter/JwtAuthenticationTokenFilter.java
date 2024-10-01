package com.ruoyi.framework.security.filter;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * token过滤器 验证token有效性
 * 
 * @author ruoyi
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Resource
    ISysUserService userService;

    @Resource
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser == null) {
            if (StringUtils.isNotEmpty(tokenService.getToken(request))) {
                loginUser = mockLoginUser(request);
            }
        }

        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
        {
            tokenService.verifyToken(loginUser);


            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }

    /**
     * 模拟登录用户，方便日常开发调试
     * <p>
     * 注意，在线上环境下，一定要关闭该功能！！！
     *
     * @param request 请求 模拟的 token，格式为 {@link TokenService#getMockSecret()} ()} + 用户编号
     * @return 模拟的 LoginUser
     */
    private LoginUser mockLoginUser(HttpServletRequest request) {
        String token = tokenService.getToken(request);
        if (!tokenService.getMockEnable()) {
            return null;
        }
        // 必须以 mockSecret 开头
        if (!token.startsWith(tokenService.getMockSecret())) {
            return null;
        }
        // 构建模拟用户
        Long userId = Long.valueOf(token.substring(tokenService.getMockSecret().length()));
        SysUser sysUser = userService.selectUserById(userId);
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userId);
        loginUser.setUser(sysUser);
        loginUser.setDeptId(sysUser.getDeptId());
        loginUser.setExpireTime(System.currentTimeMillis() + (20 * 60 * 1000L));

        return loginUser;
    }
}
