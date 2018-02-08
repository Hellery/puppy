package com.puppy.filter;

import com.puppy.enums.ResultEnum;
import com.puppy.exception.PuppyException;
import com.puppy.utils.IpUtil;
import com.puppy.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Order(1)
@WebFilter(filterName = "jwtFilter", urlPatterns = "/*")
public class JwtAuthenticationFilter implements Filter {

    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<String> patterns = new ArrayList<String>();

    @Autowired
    private JwtUtils utils;

    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (String pattern : patterns) {
            if (url.equals(pattern)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        patterns.add("identify/login");
        patterns.add("userpage");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("进入过滤器....");
        String url = request.getRequestURI().substring(request.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }
        if (isInclude(url)){
            log.info("无需验证身份....");
            filterChain.doFilter(request, response);
        }
        else {
            log.info("验证token....");
            String token = request.getHeader("Authorization");
            if (utils.validateToken(token, IpUtil.getIpAddr(request))){
                // token有效 继续请求
                filterChain.doFilter(request, response);
            }
            else {
                // token 准备跳转失败
                request.setAttribute("PUPPY_TOKEN",ResultEnum.TOKEN_INVALID);
                request.getRequestDispatcher("/error").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }

}
