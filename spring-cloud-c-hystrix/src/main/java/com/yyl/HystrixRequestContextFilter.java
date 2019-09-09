package com.yyl;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: 实现filter用于初始化HystrixRequestContext
 * @Date 2019/8/26 0026
 */
@WebFilter(filterName = "hystrixRequestContextFilter",urlPatterns = "/*",asyncSupported = true)
public class HystrixRequestContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //初始化Hystrix请求上下文
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try{
            //执行正常的请求
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            //关闭Hystrix请求上下文
            context.shutdown();
        }

    }

    @Override
    public void destroy() {

    }
}
