package com.yyl;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: 自定义异常返回json格式数据
 * @Date 2019/8/28 0028
 */
@Component
@Slf4j
public class ErrorZuulFilter  extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Throwable throwable = requestContext.getThrowable();
        if(throwable.getCause() instanceof ZuulRuntimeException){
            log.error("ZuulRuntimeException", throwable.getCause());
        }else if(throwable.getCause() instanceof ZuulException){
            log.error("ZuulException", throwable.getCause());
        }else if(throwable.getCause() instanceof RuntimeException){
            log.error("RuntimeException", throwable.getCause());
            HttpServletResponse response = requestContext.getResponse();
            response.setContentType("application/json; charset=utf8");
            response.setStatus(500);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.print("{\"code\":"+ 500 +",\"message\":\""+ throwable.getCause().getMessage() +"\"}");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(writer!=null){
                    writer.close();
                }
            }
        }
        log.error("", throwable.getCause());
        log.error("requestContext:{}", requestContext);
        log.error("receive throwable", throwable);
        return null;
    }
}
