package com.yyl;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/27 0027
 */
@Component
@Slf4j
public class TokenFilter extends ZuulFilter {
    /**
     * 过滤器类型,zuul中定义了四种
     * 1)pre:可以在请求被路由之前调用
     * 2)route:在路由被请求时调用
     * 3)error:pre、route、post阶段三阶段发生异常时会触发次过滤器，但是这个过滤器最后流向的还是post类型的过滤器
     * 因为需要post过滤器将最终结果返回给请求客户端
     * 4)post:在route和error过滤器之后被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器执行顺序 数值越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否启用过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if("yyl".equals(request.getParameter("token"))){
            log.info("request {} validation token is true",request.getRequestURL().toString());
        }else{
            log.info("token validation failure:{}",request.getParameter("token"));
            //是否对请求进行路由
            ctx.setSendZuulResponse(false);
            //设置返回的错误码
            ctx.setResponseStatusCode(401);
            //设置返回的体
            ctx.setResponseBody("token validation failure");
        }
        return null;
    }
}
